package com.canh.movie.data.model.result

import android.os.Parcelable
import com.canh.movie.data.model.Video
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoResult(
    @SerializedName("id")
    @Expose
    val id : Int,
    @SerializedName("results")
    @Expose
    val videos : List<Video>
) : Parcelable
