package com.example.srikant.networking.network
import com.example.srikant.networking.model.PopularMovie
import com.example.srikant.networking.tvseries.model.PopularTv
import com.example.srikant.networking.utilities.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkManager : INetworkManager {
    var moviesApiServices: MoviesApiServices?=null
    private constructor(){
        if(moviesApiServices==null){
            moviesApiServices = RetrofitClient.createClient()
        }
    }

    companion object {
        private var networkManager: NetworkManager?=null
        fun getInstance(): NetworkManager {
            if(networkManager ==null){
                networkManager = NetworkManager()
            }
            return networkManager!!
        }
    }

    override fun onMovieRequest(responseListener: INetworkResponse<PopularMovie>?) {
        moviesApiServices!!.getPopularMovie(
                Constants.API_KEY,
                "en-US",
                1,
                "IN"
        ).enqueue(object : Callback<PopularMovie> {
            override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
                responseListener!!.onError(t.message)
            }
            override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {
                responseListener!!.onSuccess(response.body())
            }
        })
    }

    override fun onUpcomingMovieRequest(responseListener: INetworkResponse<PopularMovie>?) {
        moviesApiServices!!.getUpcomingMovie(
                Constants.API_KEY,
                "en-US",
                1,
                "IN"
        ).enqueue(object : Callback<PopularMovie> {
            override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
                responseListener!!.onError(t.message)
            }
            override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>) {
                responseListener!!.onSuccess(response.body())
            }
        })
    }

    override fun onTvSeriesRequest(responseListener: INetworkResponse<PopularTv>?) {
        moviesApiServices!!.getPopularTv(
                Constants.API_KEY,
                "en-US",
                1
        ).enqueue(object : Callback<PopularTv> {
            override fun onFailure(call: Call<PopularTv>, t: Throwable) {
                responseListener!!.onError(t.message)
            }
            override fun onResponse(call: Call<PopularTv>, response: Response<PopularTv>) {
                responseListener!!.onSuccess(response.body())
            }
        })
    }
}