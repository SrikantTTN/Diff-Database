package com.example.srikant.networking

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.srikant.networking.dataManager.DatabaseManager
import com.example.srikant.networking.movie.view.MovieView
import com.example.srikant.networking.tvseries.view.TvSeriesView
import com.example.srikant.networking.upcoming_movie.view.UpcomingMovieView
import com.example.srikant.networking.utilities.SharedPrefHelper
import kotlinx.android.synthetic.main.main_container.*

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    private var sharedPrefHelper= SharedPrefHelper.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate")
        val p = sharedPrefHelper.getSharedPref("Style", Context.MODE_PRIVATE)
        setTheme(p.getInt("Theme", R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)
        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, MovieView()).commit();
        bottomNav.menu.findItem(R.id.movieItem).isChecked = true;
        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onNavigationItemSelected(v: MenuItem): Boolean {
        if (v.itemId == R.id.movieItem) {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, MovieView()).commit();
            return true
        }
        if (v.itemId == R.id.tvItem) {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, TvSeriesView()).commit();
            return true
        }
        if (v.itemId == R.id.upcomingMovie) {
            supportFragmentManager.beginTransaction().replace(R.id.fragContainer, UpcomingMovieView()).commit();
            return true
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val selectedId = item.itemId
        if (selectedId == R.id.darkTheme) {
            sharedPrefHelper.edit().putInt("Theme", R.style.DarkAppTheme).apply()
            recreate()
        }
        if (selectedId == R.id.lightTheme) {
            sharedPrefHelper.edit().putInt("Theme", R.style.AppTheme).apply()
            recreate()
        }
        return true
    }
    val TAG = "MainActivity"
    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt("NavPos",getSelectedItem(bottomNav))
        Log.d(TAG,"OnSaveCalled")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.d(TAG,"OnRestoreCalled")
        if(savedInstanceState!=null) {
            bottomNav.selectedItemId = savedInstanceState.getInt("NavPos")
            bottomNav.menu.findItem(savedInstanceState.getInt("NavPos")).isChecked = true
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun getSelectedItem(bottomNavigationView: BottomNavigationView): Int {
        val menu = bottomNavigationView.menu
        for (i in 0 until bottomNavigationView.menu.size()) {
            val menuItem = menu.getItem(i)
            if (menuItem.isChecked) {
                return menuItem.itemId
            }
        }
        return 0
    }
}
