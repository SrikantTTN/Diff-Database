package com.example.srikant.networking.upcoming_movie.presenter

import com.example.srikant.networking.model.PopularMovie
import com.example.srikant.networking.model.Result
import com.example.srikant.networking.network.INetworkResponse
import com.example.srikant.networking.network.NetworkManager
import com.example.srikant.networking.upcoming_movie.MovieAdapter
import com.example.srikant.networking.upcoming_movie.view.IMovieView

class MoviePresenter <T : IMovieView>(v: IMovieView) : IMoviePresenter<T> {

    private var rootView : IMovieView = v
    private var networkManager : NetworkManager = NetworkManager.getInstance()
    lateinit var mMovieList : List<Result>
    override fun getMovieList() {
        networkManager!!.onUpcomingMovieRequest(object : INetworkResponse<PopularMovie>{
            override fun onSuccess(response: PopularMovie?) {
                if(response!=null)
                    mMovieList = response.results!!
                rootView.displayMovieList(mMovieList)
            }
            override fun onError(message: String?) {
                rootView.showError(message!!)
            }
        })
    }

    override fun getMovieListCount(): Int {
        return mMovieList.size
    }

    override fun onBindRepositoryRowViewAtPosition(viewHolder: MovieAdapter.ViewHolder, position: Int) {
        viewHolder.setTitle(mMovieList[position].name!!)
        viewHolder.setOverview(mMovieList[position].overview!!)
        viewHolder.setDate(mMovieList[position].firstAirDate!!)
        viewHolder.setRating(mMovieList[position].voteAverage!!)
        if(mMovieList[position].posterPath==null){
            viewHolder.setImage("")
        }else{
            viewHolder.setImage(mMovieList[position].posterPath!!)
        }
    }

    override fun onRecyclerItemClick(position: Int) {
        rootView.showPoster(mMovieList[position])
    }
}