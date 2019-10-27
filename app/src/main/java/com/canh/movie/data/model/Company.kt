package com.canh.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("logo_path")
    @Expose
    val logoPath: String,
    @SerializedName("origin_country")
    @Expose
    val originCountry: String,
    @SerializedName("homepage")
    @Expose
    val homePage: String,
    @SerializedName("headquarters")
    @Expose
    val headQuarters: String,
    @SerializedName("description")
    @Expose
    val description: String
)