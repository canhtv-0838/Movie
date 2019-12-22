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

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> = _movies
    var isAllLoadedObservable = ObservableBoolean(false)
    var pageLoadingByCategory = MutableLiveData<Int>()
    var totalPageLoadingByCategory = MutableLiveData<Int>()

    var pageLoadingByGenre = MutableLiveData<Int>()
    var totalPageLoadingByGenre = MutableLiveData<Int>()

    override fun onCreate() {
    }

    fun getMoviesByCategoryType(@CategoryQuery categoryQuery: String, page: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByCategory(categoryQuery, language, page).getData(
            onSuccess = {
                totalPageLoadingByCategory.postValue(it.totalPages)
                pageLoadingByCategory.postValue(page)
                _movies.postValue(it.results)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }

    fun getMoviesByGenres(genresId: Int, page: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByGenres(genresId, language, page).getData(
            onSuccess = {
                totalPageLoadingByGenre.postValue(it.totalPages)
                pageLoadingByGenre.postValue(page)
                _movies.postValue(it.results)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            })
    }
}
