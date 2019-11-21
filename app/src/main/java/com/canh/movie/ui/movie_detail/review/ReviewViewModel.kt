package com.canh.movie.ui.movie_detail.review

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.response.ReviewResponse
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ReviewViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language

    private var pageLoading = 1

    private val _reviewResponse = MutableLiveData<ReviewResponse>()
    val reviewResponse: LiveData<ReviewResponse> = _reviewResponse

    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {
    }

    fun getMovieReviews(movieId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMovieReviews(movieId, language, pageLoading).getData(
            onSuccess = {
                _reviewResponse.postValue(it)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                isAllLoadedObservable.set(true)
            }
        )
    }
}