package com.canh.movie.data.model.response

import com.canh.movie.data.model.Genres
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    @Expose
    val genres: List<Genres>
)
