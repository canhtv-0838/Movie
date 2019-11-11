package com.canh.movie.network.moviedb

import com.canh.movie.data.model.*
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"

interface MovieApiRequest {

    @GET("genre/movie/list")
    fun getGenresAsync(@Query("language") language: String): Deferred<GenresResponse>

    @GET("movie/{type}")
    fun getMoviesByCategoryAsync(
        @Path("type") @CategoryQuery categoryKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<MovieResponse>

    @GET("trending/{time_window}/{media_type}")
    fun getTrendingAsync(
        @Path("media_type") @MediaType mediaType: String,
        @Path("time_window") @TimeWindow timeWindow: String
    ): Deferred<MovieResponse>

    @GET("discover/movie")
    fun getMoviesByGenresAsync(
        @Query("with_genres") genresId: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<MovieResponse>

    @GET("discover/movie")
    fun getMoviesByCastAsync(
        @Query("with_cast") castId: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("append_to_response") append: String
    ): Deferred<Movie>

    @GET("person/{person_id}")
    fun getPersonAsync(
        @Path("person_id") personId: Int,
        @Query("language") language: String
    ): Deferred<People>
}
