package com.example.srikant.networking.splash.presenter

import com.example.srikant.networking.splash.view.ISplashView

interface ISplashPresenter<T: ISplashView> {
    fun wait2Sec()
}