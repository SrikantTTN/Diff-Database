package com.example.srikant.networking.utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper private constructor(var mContext:Context):SharedPreferences {
    companion object {
        private var sharedPreferencesHelper : SharedPrefHelper?=null
        fun getInstance(context: Context): SharedPrefHelper {
            if(sharedPreferencesHelper ==null){
                sharedPreferencesHelper = SharedPrefHelper(context)
            }
            return sharedPreferencesHelper!!
        }
    }

    var sharedPrefObject: SharedPreferences?=null

    fun getSharedPref(key:String,mode:Int):SharedPreferences{
        sharedPrefObject = mContext.getSharedPreferences(key,mode)
        return sharedPrefObject!!
    }

    override fun contains(key: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInt(key: String?, defValue: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): MutableMap<String, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edit(): SharedPreferences.Editor {
        return sharedPrefObject!!.edit();
    }

    override fun getLong(key: String?, defValue: Long): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getString(key: String?, defValue: String?): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}