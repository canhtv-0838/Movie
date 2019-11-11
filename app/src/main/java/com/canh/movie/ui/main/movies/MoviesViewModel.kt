package com.canh.movie.ui.main.movies

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.Movie
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MoviesViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language
    private var pageLoading = 1

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> = _movies
    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {
    }

    fun getMoviesByCategoryType(@CategoryQuery categoryQuery: String) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByCategory(categoryQuery, language, pageLoading).getData(
            onSuccess = {
                _movies.postValue(it.results)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }

    fun getMoviesByGenres(genresId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByGenres(genresId, language, pageLoading).getData(
            onSuccess = {
                _movies.postValue(it.results)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            })
    }
}
