package com.canh.movie.ui.movie_detail.information

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.data.model.Movie
import com.canh.movie.ui.base.BaseViewModel

class InformationViewModel : BaseViewModel() {

    private val _movie = MutableLiveData<Movie>()

    val movie: LiveData<Movie> = _movie
    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {

    }

    fun postVideoResult(movie: Movie) {
        _movie.postValue(movie)
        isAllLoadedObservable.set(true)
    }

}
