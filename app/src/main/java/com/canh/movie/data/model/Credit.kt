package com.canh.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("id")
    @Expose
    val id : Int,
    @SerializedName("cast")
    @Expose
    val casts : List<Cast>
)