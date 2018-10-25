package com.example.srikant.networking

import android.app.Application
import com.example.srikant.networking.dataManager.DatabaseManager
import com.squareup.picasso.Picasso
import com.squareup.picasso.OkHttp3Downloader



class Global : Application() {
    var dataManager: DatabaseManager?=null
    override fun onCreate() {
        super.onCreate()
        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Integer.MAX_VALUE.toLong()))
        val built = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true
        Picasso.setSingletonInstance(built)
        instance=this
    }

    companion object {
        var instance : Global?=null
        fun getAppInstance():Global{
            return instance!!
        }
    }
}