package com.canh.movie.data.repository

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.Company
import com.canh.movie.data.model.Movie
import com.canh.movie.data.model.People
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

    override suspend fun getGenres(language: String): CoroutinesResult<GenresResponse> =
        remote.getGenres(language)

    override suspend fun getMoviesByCategory(
        @CategoryQuery categoryKey: String,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        remote.getMoviesByCategory(categoryKey, language, page)

    override suspend fun getMoviesByGenres(
        genresId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        remote.getMoviesByGenres(genresId, language, page)

    override suspend fun getMoviesByCast(
        castId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        remote.getMoviesByCast(castId, language, page)

    override suspend fun getMoviesByCompany(
        companyId: Int,
        language: String,
        page: Int
    ): CoroutinesResult<MovieResponse> =
        remote.getMoviesByCast(companyId, language, page)

    override suspend fun getMovieDetail(
        movieId: Int,
        language: String,
        append: String
    ): CoroutinesResult<Movie> =
        remote.getMovieDetail(movieId, language, append)

    override suspend fun getPerson(personId: Int, language: String): CoroutinesResult<People> =
        remote.getPerson(personId, language)

    override suspend fun getCompany(companyId: Int): CoroutinesResult<Company> =
        remote.getCompany(companyId)
}
