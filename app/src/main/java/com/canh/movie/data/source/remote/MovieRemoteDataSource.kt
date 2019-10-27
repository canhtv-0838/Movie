package com.canh.movie.data.source.remote

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.coroutines.awaitResult
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse
import com.canh.movie.data.source.MovieDataSource
import com.canh.movie.network.moviedb.MovieApiRequest

class MovieRemoteDataSource(private val movieApi: MovieApiRequest) : MovieDataSource.Remote {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): CoroutinesResult<MovieResponse> =
        movieApi.getTrendingAsync(mediaType,timeWindow).awaitResult()

    override suspend fun getMovieByCategory(
        @CategoryQuery categoryKey: String,
        languageCode: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        movieApi.getMovieByCategoryAsync(categoryKey, languageCode, page).awaitResult()

    override suspend fun getGenres(languageCode: String): CoroutinesResult<GenresResponse> =
        movieApi.getGenresAsync(languageCode).awaitResult()

}
