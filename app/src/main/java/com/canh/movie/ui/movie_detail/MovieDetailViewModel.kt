package com.canh.movie.ui.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.Movie
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

private const val APPEND = "credits,videos"

class MovieDetailViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language

    private val _movie = MutableLiveData<Movie>()

    val movie: LiveData<Movie> = _movie

    override fun onCreate() {

    }

    fun getMovieDetail(movieId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMovieDetail(movieId, language, APPEND).getData(
            onSuccess = {
                _movie.postValue(it)
            },
            onFailed = {
                messageNotification.postValue(it.toString())
            }
        )
    }
}
