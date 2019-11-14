package com.canh.movie.ui.producer_detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.Company
import com.canh.movie.data.model.response.MovieResponse
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ProducerDetailViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language
    private val pageLoading = 1

    private val _producer = MutableLiveData<Company>()
    private val _moviesResponse = MutableLiveData<MovieResponse>()

    val producer: LiveData<Company> = _producer
    val moviesResponse: LiveData<MovieResponse> = _moviesResponse

    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {

    }

    fun getCompanyDetail(companyId: Int) = launch(Dispatchers.IO) {
        movieRepository.getCompany(companyId).getData(
            onSuccess = {
                _producer.postValue(it)
            },
            onFailed = {
                messageNotification.postValue(it.toString())
            }
        )
    }

    fun getMovies(companyId: Int) = launch(Dispatchers.IO) {
        movieRepository.getMoviesByCompany(companyId, language, pageLoading).getData(
            onSuccess = {
                _moviesResponse.postValue(it)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }
}
