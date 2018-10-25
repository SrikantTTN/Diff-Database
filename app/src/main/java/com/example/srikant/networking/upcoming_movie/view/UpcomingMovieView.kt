package com.example.srikant.networking.upcoming_movie.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import com.example.srikant.networking.R
import com.example.srikant.networking.model.Result
import com.example.srikant.networking.upcoming_movie.MovieAdapter
import com.example.srikant.networking.upcoming_movie.presenter.MoviePresenter
import com.example.srikant.networking.utilities.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class UpcomingMovieView : androidx.fragment.app.Fragment(), IMovieView {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var mPresenter: MoviePresenter<UpcomingMovieView>
    private var mBottomSheetDialog: BottomSheetDialog? = null
    private lateinit var mBottomBehavior: BottomSheetBehavior<View>
    private lateinit var mPosterBehavior: BottomSheetBehavior<View>
    private lateinit var bottom_sheet: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.movie_fragment, container, false)
        progressBar = view.findViewById(R.id.progress_circular)
        recyclerView = view.findViewById(R.id.recyclerView)
        bottom_sheet = view.findViewById<View>(R.id.bottom_sheet)
        return view
    }

    override fun onStart() {
        super.onStart()
        mBottomBehavior = BottomSheetBehavior.from<View>(bottom_sheet)
        mPresenter.getMovieList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = MoviePresenter(this)
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE;
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun hideList() {
        recyclerView.visibility = View.GONE
    }

    override fun displayMovieList(list: List<Result>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity as Context?)
        if (activity == null) {
            Log.d("Activity", "Null")
        } else {
            var adapter = MovieAdapter(mPresenter)
            recyclerView.adapter = adapter
        }
    }

    override fun showError(message: String) {
        if (mBottomBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        val view = layoutInflater.inflate(R.layout.sheet_basic, null)
        var errorTv = view.findViewById<TextView>(R.id.errorMsg)
        errorTv.text = message
        mBottomSheetDialog = BottomSheetDialog(activity as Context)
        mBottomSheetDialog!!.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog!!.getWindow()!!.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        mBottomSheetDialog!!.show()
        mBottomSheetDialog!!.setOnDismissListener { mBottomSheetDialog = null }
    }

    override fun showPoster(movie :Result) {
        var imageUrl = Constants.POSTER_IMG_BASE_URL + movie.posterPath
        val dialog = Dialog(activity as Context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogView = LayoutInflater.from(activity as Context).inflate(R.layout.movie_view, null, false)
        val posterView: ImageView = dialogView.findViewById(R.id.poster)
        Picasso.Builder(activity as Context).build().load(imageUrl).fit().networkPolicy(NetworkPolicy.OFFLINE)
                .into(posterView, object : Callback {
                    override fun onError(e: Exception?) {
                        Picasso.Builder(activity as Context).build().load(imageUrl).into(posterView)
                    }

                    override fun onSuccess() {

                    }
                })
        val movieNameView = dialogView.findViewById<TextView>(R.id.MovieName)
        val movieOverviewView = dialogView.findViewById<TextView>(R.id.movieOverview)
        val movieRatingView = dialogView.findViewById<RatingBar>(R.id.ratingBar)
        movieNameView.text = movie.name
        movieOverviewView.text = movie.overview
        movieRatingView.rating = movie.voteAverage!!.toFloat()
        dialog.setContentView(dialogView)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()
    }
}