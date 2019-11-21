package com.canh.movie.data.source.remote

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.coroutines.awaitResult
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.Company
import com.canh.movie.data.model.Movie
import com.canh.movie.data.model.People
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse
import com.canh.movie.data.model.response.ReviewResponse
import com.canh.movie.data.source.MovieDataSource
import com.canh.movie.network.moviedb.MovieApiRequest

class MovieRemoteDataSource(private val movieApi: MovieApiRequest) : MovieDataSource.Remote {

    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): CoroutinesResult<MovieResponse> =
        movieApi.getTrendingAsync(mediaType, timeWindow).awaitResult()

    override suspend fun getMoviesByCategory(
        @CategoryQuery categoryKey: String,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        movieApi.getMoviesByCategoryAsync(categoryKey, language, page).awaitResult()

    override suspend fun getGenres(language: String): CoroutinesResult<GenresResponse> =
        movieApi.getGenresAsync(language).awaitResult()

    override suspend fun getMoviesByGenres(
        genresId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        movieApi.getMoviesByGenresAsync(genresId, language, page).awaitResult()

    override suspend fun getMoviesByCast(
        castId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        movieApi.getMoviesByCastAsync(castId, language, page).awaitResult()

    override suspend fun getMoviesByCompany(
        companyId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        movieApi.getMoviesByCompanyAsync(companyId, language, page).awaitResult()

    override suspend fun getMovieDetail(
        movieId: Int,
        language: String,
        append: String
    ): CoroutinesResult<Movie> =
        movieApi.getMovieDetailAsync(movieId, language, append).awaitResult()

    override suspend fun getPerson(personId: Int, language: String): CoroutinesResult<People> =
        movieApi.getPersonAsync(personId, language).awaitResult()

    override suspend fun getCompany(companyId: Int): CoroutinesResult<Company> =
        movieApi.getCompanyAsync(companyId).awaitResult()

    override suspend fun searchMovies(
        query: String, language: String, page: Int
    ): CoroutinesResult<MovieResponse> =
        movieApi.searchMoviesAsync(query, language, page).awaitResult()

    override suspend fun getMovieReviews(
        movieId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<ReviewResponse> =
        movieApi.getMovieReviewsAsync(movieId, language, page).awaitResult()
}
