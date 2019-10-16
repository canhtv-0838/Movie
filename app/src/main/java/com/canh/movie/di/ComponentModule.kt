package com.canh.movie.di

import androidx.databinding.PropertyChangeRegistry
import com.canh.movie.utils.KoinNames
import org.koin.core.qualifier.named
import org.koin.dsl.module

val componentModule = module {
    single (named(KoinNames.PROPERTY_CHANGE_REGISTRY)){ PropertyChangeRegistry() }
}