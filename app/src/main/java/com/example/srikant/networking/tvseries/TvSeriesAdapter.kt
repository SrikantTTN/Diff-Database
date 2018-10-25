package com.example.srikant.networking.movie

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.srikant.networking.R
import com.example.srikant.networking.tvseries.model.Result
import com.example.srikant.networking.utilities.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class TvSeriesAdapter(private var mContext:Context,private var mList:List<Result>) : androidx.recyclerview.widget.RecyclerView.Adapter<TvSeriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.tv_item,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var result = mList.get(p1)
        var imageUrl = Constants.POSTER_IMG_BASE_URL + result.posterPath
        p0.movieHeading.text= result.name
        p0.movieContent.text= result.overview
        p0.rating.rating = result.voteAverage!!.toFloat()
        if(result.firstAirDate==null || result.firstAirDate.equals("")){
            p0.dateOfRelease.text = result.releaseDate
        }
        else {
            p0.dateOfRelease.text = result.firstAirDate
        }
        Picasso.Builder(mContext).build().load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE)
                .into(p0.moviePoster,object:Callback{
                    override fun onError(e: Exception?) {
                        Picasso.Builder(mContext).build().load(imageUrl).into(p0.moviePoster)

                    }

                    override fun onSuccess() {
                    }

                })
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var moviePoster = itemView.findViewById<ImageView>(R.id.moviePoster)
        var movieHeading = itemView.findViewById<TextView>(R.id.movieHeading)
        var movieContent = itemView.findViewById<TextView>(R.id.contentDescription)
        var rating = itemView.findViewById<RatingBar>(R.id.ratingBar)
        var dateOfRelease = itemView.findViewById<TextView>(R.id.date)
    }
}