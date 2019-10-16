package com.canh.movie.ui.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canh.movie.utils.KoinNames
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent, Observable {

    private val callbacks: PropertyChangeRegistry = get(named(KoinNames.PROPERTY_CHANGE_REGISTRY))

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) =
        callbacks.add(callback)

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) =
        callbacks.remove(callback)

    fun notifyChange() = callbacks.notifyCallbacks(this, 0, null)

    fun notifyPropertyChanged(fieldId: Int) = callbacks.notifyCallbacks(this, fieldId, null)

    abstract fun create()
}