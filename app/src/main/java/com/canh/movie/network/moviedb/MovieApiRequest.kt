package com.canh.movie.network.moviedb

import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.MediaType
import com.canh.movie.data.model.Movie
import com.canh.movie.data.model.TimeWindow
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.model.response.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"
const val PAGE_DEFAULT = 1
const val LANGUAGE_DEFAULT = "en"

interface MovieApiRequest {

    @GET("genre/movie/list")
    fun getGenresAsync(@Query("language") languageCode: String = LANGUAGE_DEFAULT): Deferred<GenresResponse>

    @GET("movie/{type}")
    fun getMoviesByCategoryAsync(
        @Path("type") @CategoryQuery categoryKey: String,
        @Query("language") language: String = LANGUAGE_DEFAULT,
        @Query("page") page: Int = PAGE_DEFAULT
    ): Deferred<MovieResponse>

    @GET("trending/{time_window}/{media_type}")
    fun getTrendingAsync(
        @Path("media_type") @MediaType mediaType: String,
        @Path("time_window") @TimeWindow timeWindow: String
    ): Deferred<MovieResponse>

    @GET("discover/movie")
    fun getMoviesByGenresAsync(
        @Query("with_genres") genresId: Int,
        @Query("language") language: String = LANGUAGE_DEFAULT,
        @Query("page") page: Int = PAGE_DEFAULT
    ): Deferred<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(
        @Path("movie_id") movieId: Int,
        @Query("language") languageCode: String,
        @Query("append_to_response") append: String
    ) : Deferred<Movie>
}
