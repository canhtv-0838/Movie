package com.canh.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.response.GenresResponse
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {
    private val language = Locale.getDefault().language

    private val _genresResponse = MutableLiveData<GenresResponse>()

    val genresResponse: LiveData<GenresResponse> = _genresResponse

    override fun onCreate() {
        getGenres()
    }

    private fun getGenres() = launch(Dispatchers.IO) {
        movieRepository.getGenres(language).getData(
            onSuccess = {
                _genresResponse.postValue(GenresResponse(it.genres))
            },
            onFailed = {
                messageNotification.postValue(it.message.toString())
            }
        )
    }
}
