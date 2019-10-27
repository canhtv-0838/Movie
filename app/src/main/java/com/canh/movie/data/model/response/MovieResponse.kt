package com.canh.movie.data.model.response

import com.canh.movie.data.model.Date
import com.canh.movie.data.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page")
    @Expose
    val page : Int,
    @SerializedName("results")
    @Expose
    val results : List<Movie>,
    @SerializedName("dates")
    @Expose
    val dates : Date,
    @SerializedName("total_pages")
    @Expose
    val totalPages : Int,
    @SerializedName("total_results")
    @Expose
    val totalResults : Int
)
