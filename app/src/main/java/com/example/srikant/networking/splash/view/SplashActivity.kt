package com.example.srikant.networking.splash.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.srikant.networking.MainActivity
import com.example.srikant.networking.R
import com.example.srikant.networking.splash.presenter.SplashPresenter

class SplashActivity: ISplashView, Activity() {
    private lateinit var mPresenter : SplashPresenter<ISplashView>
    override fun finishClearTop() {
        var intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        mPresenter = SplashPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.wait2Sec()
    }
}