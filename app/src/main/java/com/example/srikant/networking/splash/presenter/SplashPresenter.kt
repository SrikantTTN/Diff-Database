package com.example.srikant.networking.splash.presenter

import android.os.Handler
import com.example.srikant.networking.splash.view.ISplashView
import com.example.srikant.networking.splash.view.SplashActivity


class SplashPresenter<T : ISplashView> : ISplashPresenter<T> {
    private var splashActivity : SplashActivity
    constructor(view : SplashActivity){
        splashActivity = view
    }
    override fun wait2Sec() {
        Handler().postDelayed({
            splashActivity.finishClearTop()
        },2000)
    }
}
