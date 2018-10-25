package com.example.srikant.networking.network

import com.example.srikant.networking.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient  {

    companion object {
        var retrofitClient:Retrofit?=null

        var okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else
                        HttpLoggingInterceptor.Level.NONE
                })
                .build()

        fun createClient() : MoviesApiServices {
            if (retrofitClient == null) {
                retrofitClient = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .baseUrl("https://api.themoviedb.org/3/")
                        .build()
            }
            return retrofitClient!!.create(MoviesApiServices::class.java);
        }
    }
}