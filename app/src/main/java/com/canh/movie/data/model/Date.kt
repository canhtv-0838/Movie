package com.canh.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("maximum")
    @Expose
    val maximum : String,

    @SerializedName("minimum")
    @Expose
    val minimum : String
)
