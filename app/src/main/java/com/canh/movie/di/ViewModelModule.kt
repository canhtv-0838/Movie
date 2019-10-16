package com.canh.movie.di

import com.canh.movie.ui.landing.LandingViewModel
import com.canh.movie.ui.landing.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }

    viewModel { LandingViewModel() }
}