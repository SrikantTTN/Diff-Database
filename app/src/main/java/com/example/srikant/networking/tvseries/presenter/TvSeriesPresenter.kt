package com.example.srikant.networking.tvseries.presenter

import com.example.srikant.networking.network.INetworkResponse
import com.example.srikant.networking.network.NetworkManager
import com.example.srikant.networking.tvseries.model.PopularTv
import com.example.srikant.networking.tvseries.model.Result
import com.example.srikant.networking.tvseries.view.TvSeriesView


class TvSeriesPresenter <T : TvSeriesView> : ITvSeriesPresenter<T> {
    private var rootView : TvSeriesView
    private var networkManager : NetworkManager
    lateinit var mTvSeriesList : List<Result>
    constructor(v : TvSeriesView){
        this.rootView = v;
        networkManager = NetworkManager.getInstance()
    }
    override fun getTvList() {
        networkManager!!.onTvSeriesRequest(object: INetworkResponse<PopularTv> {
            override fun onSuccess(response: PopularTv?){
                if(response!=null){
                    mTvSeriesList = response.results!!
                    rootView.hideLoader()
                    rootView.displayTvList(mTvSeriesList)
                }
            }
            override fun onError(message : String?){
                    rootView.hideLoader()
                    rootView.showError(message!!)
            }
        })
    }
}