package com.example.srikant.networking.upcoming_movie.presenter

import com.example.srikant.networking.upcoming_movie.MovieAdapter
import com.example.srikant.networking.upcoming_movie.view.IMovieView

interface IMoviePresenter<T : IMovieView> {
    fun getMovieList()
    fun getMovieListCount():Int
    fun onBindRepositoryRowViewAtPosition(viewHolder: MovieAdapter.ViewHolder,position :Int)
    fun onRecyclerItemClick(position : Int)
}