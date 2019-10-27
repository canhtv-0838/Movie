package com.canh.movie.data.model.result

import com.canh.movie.data.model.Video
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("id")
    @Expose
    val id : Int,
    @SerializedName("results")
    @Expose
    val videos : List<Video>
)
