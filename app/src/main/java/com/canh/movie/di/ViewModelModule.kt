package com.canh.movie.di

import com.canh.movie.ui.landing.LandingViewModel
import com.canh.movie.ui.landing.splash.SplashViewModel
import com.canh.movie.ui.main.MainViewModel
import com.canh.movie.ui.main.home.HomeViewModel
import com.canh.movie.utils.KoinNames
import com.canh.movie.utils.KoinNames.APP_CONTEXT
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }

    viewModel { LandingViewModel() }

    viewModel { MainViewModel(get(named(KoinNames.MOVIE_REPOSITORY))) }

    viewModel {
        HomeViewModel(
            get(named(KoinNames.MOVIE_REPOSITORY)),
            get(named(APP_CONTEXT))
        )
    }
}
