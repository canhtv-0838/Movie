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
import kotlin.collections.ArrayList

class MoviesViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> = _movies
    var isAllLoadedObservable = ObservableBoolean(false)

    var pageLoadingByCategory = 1
    var tempMoviesByCategory = ArrayList<Movie>()

    var pageLoadingByGenre = 1
    var tempMoviesByGenre = ArrayList<Movie>()


    override fun onCreate() {
    }

    fun getMoviesByCategoryType(@CategoryQuery categoryQuery: String) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByCategory(categoryQuery, language, pageLoadingByCategory).getData(
            onSuccess = {
                if (pageLoadingByCategory < it.totalPages) {
                    tempMoviesByCategory.addAll(it.results)
                    _movies.postValue(tempMoviesByCategory)
                    isAllLoadedObservable.set(true)
                    pageLoadingByCategory++
                } else {
                    tempMoviesByCategory.addAll(it.results)
                    _movies.postValue(tempMoviesByCategory)
                    isAllLoadedObservable.set(true)
                }
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }

    fun getMoviesByGenres(genresId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByGenres(genresId, language, pageLoadingByGenre).getData(
            onSuccess = {
                if (pageLoadingByGenre < it.totalPages) {
                    tempMoviesByGenre.addAll(it.results)
                    _movies.postValue(tempMoviesByGenre)
                    isAllLoadedObservable.set(true)
                    pageLoadingByGenre++
                }
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            })
    }
}
