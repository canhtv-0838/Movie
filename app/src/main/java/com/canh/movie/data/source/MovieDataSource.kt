package com.canh.movie.data.source

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.data.model.*
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse

interface MovieDataSource {

    interface Remote {
        suspend fun getGenres(languageCode: String): CoroutinesResult<GenresResponse>

        suspend fun getMoviesByCategory(
            @CategoryQuery categoryKey: String,
            language: String,
            page: Int
        ): CoroutinesResult<MovieResponse>

        suspend fun getTrending(
            @MediaType mediaType: String,
            @TimeWindow timeWindow: String
        ): CoroutinesResult<MovieResponse>

        suspend fun getMoviesByGenres(
            genresId: Int,
            language: String,
            page: Int
        ): CoroutinesResult<MovieResponse>

        suspend fun getMoviesByCast(
            castId: Int,
            language: String,
            page: Int
        ): CoroutinesResult<MovieResponse>

        suspend fun getMoviesByCompany(
            companyId: Int,
            language: String,
            page: Int
        ): CoroutinesResult<MovieResponse>

        suspend fun getMovieDetail(
            movieId: Int,
            language: String,
            append: String
        ): CoroutinesResult<Movie>

        suspend fun getPerson(
            personId: Int,
            language: String
        ): CoroutinesResult<People>

        suspend fun getCompany(
            companyId: Int
        ): CoroutinesResult<Company>

    }
}
