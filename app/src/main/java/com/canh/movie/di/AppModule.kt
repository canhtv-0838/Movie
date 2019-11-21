package com.canh.movie.di

import com.canh.movie.network.RetrofitBuilder
import com.canh.movie.network.mine.MINE_DB_BASE_URL
import com.canh.movie.network.mine.MineApiRequest
import com.canh.movie.network.moviedb.MOVIE_DB_BASE_URL
import com.canh.movie.network.moviedb.MovieApiRequest
import com.canh.movie.utils.KoinNames
import com.canh.movie.utils.KoinNames.APP_CONTEXT
import com.canh.movie.utils.KoinNames.COROUTINES_CALL_ADAPTER_FACTORY
import com.canh.movie.utils.KoinNames.GSON_CONVERTER_FACTORY
import com.canh.movie.utils.KoinNames.MINE_BASE_URL
import com.canh.movie.utils.KoinNames.MOVIE_BASE_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single(named(APP_CONTEXT)) { androidApplication() }

    single(named(KoinNames.API_MOVIE)) {
        RetrofitBuilder.builderApi(
            context = get(named(APP_CONTEXT)),
            baseUrl = get(named(MOVIE_BASE_URL)),
            requestApi = MovieApiRequest::class.java,
            converterFactory = get(named(GSON_CONVERTER_FACTORY)),
            callAdapterFactory = get(named(COROUTINES_CALL_ADAPTER_FACTORY))
        )
    }

    single(named(KoinNames.API_MINE)) {
        RetrofitBuilder.builderApi(
            context = get(named(APP_CONTEXT)),
            baseUrl = get(named(MINE_BASE_URL)),
            requestApi = MineApiRequest::class.java,
            converterFactory = get(named(GSON_CONVERTER_FACTORY)),
            callAdapterFactory = get(named(COROUTINES_CALL_ADAPTER_FACTORY))
        )
    }

    single(named(MOVIE_BASE_URL)) {
        MOVIE_DB_BASE_URL
    }

    single(named(MINE_BASE_URL)) {
        MINE_DB_BASE_URL
    }

    single(named(GSON_CONVERTER_FACTORY)) {
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }

    single(named(COROUTINES_CALL_ADAPTER_FACTORY)) {
        CoroutineCallAdapterFactory()
    }
}
