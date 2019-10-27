package com.canh.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("iso_639_1")
    @Expose
    val iso_639_1: String,
    @SerializedName("iso_3166_1")
    @Expose
    val iso_3166_1: String,
    @SerializedName("key")
    @Expose
    val key: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("site")
    @Expose
    val site: String,
    @SerializedName("size")
    @Expose
    val size: Int,
    @SerializedName("type")
    @Expose
    val type: String
)
