package com.example.srikant.networking.tvseries.view

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.example.srikant.networking.R
import com.example.srikant.networking.movie.TvSeriesAdapter
import com.example.srikant.networking.tvseries.model.Result
import com.example.srikant.networking.tvseries.presenter.TvSeriesPresenter

class TvSeriesView : androidx.fragment.app.Fragment(), ITvSeriesView {
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var mPresenter : TvSeriesPresenter<TvSeriesView>
    private var mBottomSheetDialog : BottomSheetDialog? = null
    private lateinit var mBehavior : BottomSheetBehavior<View>
    private lateinit var bottom_sheet : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.movie_fragment,container,false)
        progressBar = view.findViewById(R.id.progress_circular)
        recyclerView = view.findViewById(R.id.recyclerView)
        bottom_sheet = view.findViewById<View>(R.id.bottom_sheet)
        return view
    }

    override fun onStart() {
        super.onStart()
        mBehavior = BottomSheetBehavior.from<View>(bottom_sheet)
        mPresenter.getTvList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = TvSeriesPresenter(this)
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
    override fun displayTvList(list: List<Result>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity as Context?)
        if(activity==null){
            Log.d("Activity","Null")
        }else {
            var adapter = TvSeriesAdapter(activity as Context, list)
            recyclerView.adapter = adapter
        }
    }
    override fun showError(message: String) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
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
        mBottomSheetDialog!!.setOnDismissListener(DialogInterface.OnDismissListener { mBottomSheetDialog = null })
    }
}