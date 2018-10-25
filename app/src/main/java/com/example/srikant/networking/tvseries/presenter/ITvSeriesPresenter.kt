package com.example.srikant.networking.tvseries.presenter
import com.example.srikant.networking.tvseries.view.ITvSeriesView

interface ITvSeriesPresenter<T : ITvSeriesView> {
    fun getTvList()
}