package com.example.srikant.networking.movie.view

import com.example.srikant.networking.model.Result

interface IMovieView {
    fun displayMovieList(list:List<Result>)
    fun showLoader()
    fun hideLoader()
    fun hideList()
    fun showError(message:String)
    fun showPoster(movie:Result)

}