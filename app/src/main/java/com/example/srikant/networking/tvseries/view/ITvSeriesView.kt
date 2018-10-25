package com.example.srikant.networking.tvseries.view

import com.example.srikant.networking.tvseries.model.Result

interface ITvSeriesView {
    fun displayTvList(list:List<Result>)
    fun showLoader()
    fun hideLoader()
    fun hideList()
    fun showError(message:String)
}