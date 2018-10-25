package com.example.srikant.networking.movie

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.srikant.networking.R
import com.example.srikant.networking.movie.presenter.MoviePresenter
import com.example.srikant.networking.movie.view.MovieView
import com.example.srikant.networking.utilities.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class MovieAdapter(var moviePresenter: MoviePresenter<MovieView>) : androidx.recyclerview.widget.RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.tv_item, p0, false)
        return ViewHolder(view,moviePresenter)
    }

    override fun getItemCount(): Int {
        return moviePresenter.getMovieListCount()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        moviePresenter.onBindRepositoryRowViewAtPosition(p0, p1)
    }

    class ViewHolder(itemView: View,var moviePresenter: MoviePresenter<MovieView>) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), IMovieHolder,View.OnClickListener {

        private var moviePoster = itemView.findViewById<ImageView>(R.id.moviePoster)
        private var movieHeading = itemView.findViewById<TextView>(R.id.movieHeading)
        private var movieContent = itemView.findViewById<TextView>(R.id.contentDescription)
        private var rating = itemView.findViewById<RatingBar>(R.id.ratingBar)
        private var dateOfRelease = itemView.findViewById<TextView>(R.id.date)

        init {
            itemView.setOnClickListener(this)
        }

        override fun setOverview(overview: String) {
            movieContent.text = overview
        }

        override fun setRating(ratingOfMovie: Double) {
            rating.rating = ratingOfMovie.toFloat()
        }

        override fun setDate(date: String) {
            dateOfRelease.text = date
        }

        override fun setImage(path: String) {
            var imageUrl = Constants.POSTER_IMG_BASE_URL + path
            Picasso.Builder(itemView.context).build().load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE)
                    .into(moviePoster, object : Callback {
                        override fun onError(e: Exception?) {
                            Picasso.Builder(itemView.context).build().load(imageUrl).into(moviePoster)
                        }

                        override fun onSuccess() {
                        }
                    })

        }

        override fun setTitle(title: String) {
            movieHeading.text = title
        }

        override fun onClick(v: View?) {
            moviePresenter.onRecyclerItemClick(adapterPosition)
        }
    }
}