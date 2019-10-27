package com.canh.movie.data.model.pair

import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.response.MovieResponse

data class CategoryPair(
    @CategoryQuery val categoryName: String,
    var movieResponse: MovieResponse
)
