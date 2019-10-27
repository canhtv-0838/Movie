package com.canh.movie.data.source

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.MediaType
import com.canh.movie.data.model.TimeWindow
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse

interface MovieDataSource {

    interface Remote {
        suspend fun getGenres(languageCode: String): CoroutinesResult<GenresResponse>

        suspend fun getMovieByCategory(
            @CategoryQuery categoryKey: String,
            languageCode: String,
            page: Int
        ): CoroutinesResult<MovieResponse>

        suspend fun getTrending(
            @MediaType mediaType: String,
            @TimeWindow timeWindow: String
        ): CoroutinesResult<MovieResponse>
    }
}
