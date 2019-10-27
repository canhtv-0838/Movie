package com.canh.movie.di

import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.data.source.remote.MovieRemoteDataSource
import com.canh.movie.utils.KoinNames.API_MOVIE
import com.canh.movie.utils.KoinNames.MOVIE_REMOTE_DATA_SOURCE
import com.canh.movie.utils.KoinNames.MOVIE_REPOSITORY
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sourceModule = module {
    single(named(MOVIE_REMOTE_DATA_SOURCE)) {
        MovieRemoteDataSource(movieApi = get(named(API_MOVIE)))
    }

    single(named(MOVIE_REPOSITORY)) {
        MovieRepository(remote = get(named(MOVIE_REMOTE_DATA_SOURCE)))
    }
}
