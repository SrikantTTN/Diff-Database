package com.example.srikant.networking.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.example.srikant.networking.network.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PopularMovie() : Parcelable, BaseResponse {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    constructor(parcel: Parcel) : this() {
        page = parcel.readValue(Int::class.java.classLoader) as? Int
        totalResults = parcel.readValue(Int::class.java.classLoader) as? Int
        totalPages = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(page)
        parcel.writeValue(totalResults)
        parcel.writeValue(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularMovie> {
        override fun createFromParcel(parcel: Parcel): PopularMovie {
            return PopularMovie(parcel)
        }

        override fun newArray(size: Int): Array<PopularMovie?> {
            return arrayOfNulls(size)
        }
    }

}
