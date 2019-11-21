package com.canh.movie.di

import com.canh.movie.data.repository.MineRepository
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.data.source.remote.MineRemoteDataSource
import com.canh.movie.data.source.remote.MovieRemoteDataSource
import com.canh.movie.utils.KoinNames.API_MINE
import com.canh.movie.utils.KoinNames.API_MOVIE
import com.canh.movie.utils.KoinNames.MINE_REMOTE_DATA_SOURCE
import com.canh.movie.utils.KoinNames.MINE_REPOSITORY
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

    single(named(MINE_REMOTE_DATA_SOURCE)) {
        MineRemoteDataSource(mineApi = get(named(API_MINE)))
    }

    single(named(MINE_REPOSITORY)) {
        MineRepository(remote = get(named(MINE_REMOTE_DATA_SOURCE)))
    }

}
