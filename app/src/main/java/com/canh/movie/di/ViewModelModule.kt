package com.canh.movie.di

import com.canh.movie.ui.cast_detail.CastDetailViewModel
import com.canh.movie.ui.cast_detail.information.CastInformationViewModel
import com.canh.movie.ui.cast_detail.movies.CastMoviesViewModel
import com.canh.movie.ui.landing.LandingViewModel
import com.canh.movie.ui.landing.login.LoginViewModel
import com.canh.movie.ui.landing.splash.SplashViewModel
import com.canh.movie.ui.main.MainViewModel
import com.canh.movie.ui.main.home.HomeViewModel
import com.canh.movie.ui.main.movies.MoviesViewModel
import com.canh.movie.ui.movie_detail.MovieDetailViewModel
import com.canh.movie.ui.movie_detail.cast.CastViewModel
import com.canh.movie.ui.movie_detail.information.InformationViewModel
import com.canh.movie.ui.movie_detail.producer.ProducerViewModel
import com.canh.movie.ui.movie_detail.trailer.TrailerViewModel
import com.canh.movie.ui.producer_detail.ProducerDetailViewModel
import com.canh.movie.ui.search.SearchViewModel
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

    viewModel {
        MoviesViewModel(get(named(KoinNames.MOVIE_REPOSITORY)))
    }

    viewModel {
        MovieDetailViewModel(get(named(KoinNames.MOVIE_REPOSITORY)))
    }

    viewModel { TrailerViewModel() }

    viewModel { InformationViewModel() }

    viewModel { LoginViewModel() }

    viewModel { CastViewModel() }

    viewModel { ProducerViewModel() }

    viewModel {
        CastDetailViewModel(get(named(KoinNames.MOVIE_REPOSITORY)))
    }

    viewModel { CastInformationViewModel() }

    viewModel {
        CastMoviesViewModel(get(named(KoinNames.MOVIE_REPOSITORY)))
    }

    viewModel {
        ProducerDetailViewModel(get(named(KoinNames.MOVIE_REPOSITORY)))
    }

    viewModel { SearchViewModel() }
}
