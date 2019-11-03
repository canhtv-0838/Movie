package com.canh.movie.di

import androidx.databinding.PropertyChangeRegistry
import com.canh.movie.utils.KoinNames.PROPERTY_CHANGE_REGISTRY
import com.canh.movie.utils.KoinNames.SHARED_PREFERENCES
import com.canh.movie.utils.SharedPreference
import org.koin.core.qualifier.named
import org.koin.dsl.module

val componentModule = module {
    single(named(PROPERTY_CHANGE_REGISTRY)) { PropertyChangeRegistry() }

    single(named(SHARED_PREFERENCES)) { SharedPreference(get(named(SHARED_PREFERENCES))) }
}
