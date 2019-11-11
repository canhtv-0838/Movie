package com.canh.movie.ui.cast_detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.People
import com.canh.movie.data.repository.MovieRepository
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CastDetailViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private val language = Locale.getDefault().language

    private val _person = MutableLiveData<People>()
    val person: LiveData<People> = _person

    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {

    }

    fun getCastDetail(personId: Int) = launch(Dispatchers.IO) {
        movieRepository.getPerson(personId, language).getData(
            onSuccess = {
                _person.postValue(it)
                isAllLoadedObservable.set(true)
            },
            onFailed = {
                messageNotification.postValue(it.toString())
                isAllLoadedObservable.set(true)
            }
        )
    }
}
