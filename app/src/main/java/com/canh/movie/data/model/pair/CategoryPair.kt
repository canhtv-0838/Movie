package com.canh.movie.data.model.pair

import com.canh.movie.data.model.CategoryName
import com.canh.movie.data.model.response.MovieResponse

data class CategoryPair(
    @CategoryName val categoryName: String,
    var movieResponse: MovieResponse
)
