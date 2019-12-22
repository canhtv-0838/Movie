package com.canh.movie.ui.search

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
import kotlin.collections.ArrayList

class SearchViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language
    var pageLoading = 1

    var tempMovies = ArrayList<Movie>()

    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> = _movies
    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {
        isAllLoadedObservable.set(true)
    }

    fun searchMovies(query: String) = launch(Dispatchers.IO) {
        isAllLoadedObservable.set(false)
        movieRepository.searchMovies(query, language, pageLoading).getData(
            onSuccess = {
                if (pageLoading<it.totalPages){
                    tempMovies.addAll(it.results)
                    _movies.postValue(tempMovies)
                    isAllLoadedObservable.set(true)
                    pageLoading++
                } else {
                    tempMovies.addAll(it.results)
                    _movies.postValue(tempMovies)
                    isAllLoadedObservable.set(true)
                }

            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }

}
