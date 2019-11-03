package com.canh.movie.ui.movie_detail.trailer

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.data.model.result.VideoResult
import com.canh.movie.ui.base.BaseViewModel

class TrailerViewModel : BaseViewModel() {

    private val _videoResult = MutableLiveData<VideoResult>()

    val videoResult: LiveData<VideoResult> = _videoResult
    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {

    }

    fun postVideoResult(videoResult: VideoResult) {
        _videoResult.postValue(videoResult)
        isAllLoadedObservable.set(true)
    }

}
