package com.canh.movie.ui.cast_detail.movies

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.Movie
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CastMoviesViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language
    private val pageLoading = 1

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    val isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {
    }

    fun getMoviesByCast(castId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByCast(castId, language, pageLoading).getData(
            onSuccess = {
                _movies.postValue(it.results)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }
}
