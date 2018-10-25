package com.example.srikant.networking.movie.presenter

import android.database.sqlite.SQLiteCantOpenDatabaseException
import com.example.srikant.networking.dataManager.DatabaseManager
import com.example.srikant.networking.model.PopularMovie
import com.example.srikant.networking.model.Result
import com.example.srikant.networking.movie.MovieAdapter
import com.example.srikant.networking.movie.view.IMovieView
import com.example.srikant.networking.network.INetworkResponse
import com.example.srikant.networking.network.NetworkManager
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MoviePresenter <T : IMovieView>(v: IMovieView) : IMoviePresenter<T> {

    private var rootView : IMovieView = v
    private var networkManager : NetworkManager = NetworkManager.getInstance()
    private var databaseManager: DatabaseManager = DatabaseManager.getInstance()
    private var mMovieList : List<Result>?=null

    override fun getMovieList() {
        async(UI) {
            var tempList = bg {fetchMoviesFromDb()}
            mMovieList = tempList.await()
                if (mMovieList == null || mMovieList!!.isEmpty()) {
                    fetchMoviesFromApi()
                } else {
                    rootView.hideLoader()
                    rootView.displayMovieList(mMovieList!!)
                }

        }
    }
    private fun fetchMoviesFromDb():List<Result>?  {
        var list:List<Result>?
        return try{
            list = databaseManager.resultDao().resultList()
            list
        }catch (error: SQLiteCantOpenDatabaseException){
            null
        }
    }

    private fun fetchMoviesFromApi(){
        networkManager!!.onMovieRequest(object: INetworkResponse<PopularMovie> {
            override fun onSuccess(response: PopularMovie?){
                if(response!=null){
                    mMovieList = response.results!!
                    rootView.hideLoader()
                    rootView.displayMovieList(mMovieList!!)
                    async(UI) {
                        for(results in mMovieList!!)
                        bg{databaseManager.resultDao().insertResult(results)}
                    }

                }
            }
            override fun onError(message : String?){
                rootView.hideLoader()
                rootView.showError(message!!)
            }
        })
    }



    override fun getMovieListCount(): Int {
        return mMovieList!!.size
    }

    override fun onBindRepositoryRowViewAtPosition(viewHolder: MovieAdapter.ViewHolder, position: Int) {
        viewHolder.setTitle(mMovieList!![position].name!!)
        viewHolder.setOverview(mMovieList!![position].overview!!)
        viewHolder.setDate(mMovieList!![position].firstAirDate!!)
        viewHolder.setRating(mMovieList!![position].voteAverage!!)
        viewHolder.setImage(mMovieList!![position].posterPath!!)
    }

    override fun onRecyclerItemClick(position: Int) {
        rootView.showPoster(mMovieList!![position])
    }
}