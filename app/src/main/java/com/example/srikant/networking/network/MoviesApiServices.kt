package com.example.srikant.networking.network

import com.example.srikant.networking.model.PopularMovie
import com.example.srikant.networking.tvseries.model.PopularTv
import retrofit2.Call
import retrofit2.http.*

interface MoviesApiServices{
    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") api_key: String,
                      @Query("language") language: String,
                      @Query("page") pageCount: Int) : Call<PopularTv>
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") api_key: String,
                     @Query("language") language: String,
                     @Query("page") pageCount: Int,
                     @Query("region") region :String): Call<PopularMovie>
    @GET("movie/upcoming")
    fun getUpcomingMovie(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("page") pageCount: Int,
                         @Query("region") region :String): Call<PopularMovie>


}