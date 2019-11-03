package com.canh.movie.ui.movie_detail.cast

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.data.model.Cast
import com.canh.movie.ui.base.BaseViewModel

class CastViewModel : BaseViewModel() {
    private val _casts = MutableLiveData<List<Cast>>()

    val casts: LiveData<List<Cast>> = _casts
    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {

    }

    fun postCasts(casts: List<Cast>) {
        _casts.postValue(casts)
        isAllLoadedObservable.set(true)
    }

}
