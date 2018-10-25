package com.example.srikant.networking.movie

interface IMovieHolder {
    fun setTitle(title:String)
    fun setOverview(overview:String)
    fun setRating(rating:Double)
    fun setDate(date:String)
    fun setImage(imageUrl:String)
}