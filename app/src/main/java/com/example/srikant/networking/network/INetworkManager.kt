package com.example.srikant.networking.network

import com.example.srikant.networking.model.PopularMovie
import com.example.srikant.networking.tvseries.model.PopularTv

interface INetworkManager {
    fun onMovieRequest(responseListener: INetworkResponse<PopularMovie>?)
    fun onUpcomingMovieRequest(responseListener: INetworkResponse<PopularMovie>?)
    fun onTvSeriesRequest(responseListener: INetworkResponse<PopularTv>?)
}