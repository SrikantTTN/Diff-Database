package com.example.srikant.networking.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popularMovie",indices =[Index(value = "id",
        unique = true)])
class Result() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var rowId:Int=0

    @SerializedName("title")
    @Expose
    var name: String? = null
    @SerializedName("release_date")
    @Expose
    var firstAirDate: String? = null
    @ColumnInfo()
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        firstAirDate = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        overview = parcel.readString()
        posterPath = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(firstAirDate)
        parcel.writeValue(id)
        parcel.writeValue(voteAverage)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }

}