package com.canh.movie.data.repository

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse
import com.canh.movie.data.source.MovieDataSource

class MovieRepository(private val remote: MovieDataSource.Remote) :
    MovieDataSource.Remote {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): CoroutinesResult<MovieResponse> =
        remote.getTrending(timeWindow, mediaType)

    override suspend fun getGenres(languageCode: String): CoroutinesResult<GenresResponse> =
        remote.getGenres(languageCode)

    override suspend fun getMovieByCategory(
        @CategoryQuery categoryKey: String,
        languageCode: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        remote.getMovieByCategory(categoryKey, languageCode, page)
}
