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
    private var pageLoadingByCategory = 1
    private var pageLoadingByGenres = 1

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> = _movies
    var isAllLoadedObservable = ObservableBoolean(false)

    var temp : MutableList<Movie> = emptyList<Movie>().toMutableList()
    override fun onCreate() {
    }

    fun getMoviesByCategoryType(@CategoryQuery categoryQuery: String) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByCategory(categoryQuery, language, pageLoadingByCategory).getData(
            onSuccess = {
                temp.addAll(it.results)
                _movies.postValue(temp)
                pageLoadingByCategory++
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }

    fun getMoviesByGenres(genresId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByGenres(genresId, language, pageLoadingByGenres).getData(
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
