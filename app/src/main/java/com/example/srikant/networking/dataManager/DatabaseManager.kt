package com.example.srikant.networking.dataManager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.srikant.networking.Global
import com.example.srikant.networking.model.Result
import java.util.*

@Database(entities = [(Result::class)],version = 1)
abstract class DatabaseManager : RoomDatabase() {
    companion object {
        private val DATABASE_NAME="MoviesDB.db"
        private val LOCK = Object()
        private var dataManager : DatabaseManager?=null
        fun getInstance():DatabaseManager{
            if(dataManager==null) {
                synchronized(LOCK) {
                dataManager = Room.databaseBuilder(Global.getAppInstance(),
                        DatabaseManager::class.java, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build()
            }
            }
            return dataManager!!
        }
    }

    abstract fun resultDao():ResultDAO
}