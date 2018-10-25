package com.example.srikant.networking.network

interface INetworkResponse<T :BaseResponse> {

    fun onSuccess(response: T?)
    fun onError(message : String?)
}