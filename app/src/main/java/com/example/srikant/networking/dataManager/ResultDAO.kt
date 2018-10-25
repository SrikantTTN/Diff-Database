package com.example.srikant.networking.dataManager

import androidx.room.*
import com.example.srikant.networking.model.Result
@Dao
interface ResultDAO {

    @Query("select * from popularMovie")
    fun resultList() :List<Result>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(result :Result)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateResult(result :Result)
}