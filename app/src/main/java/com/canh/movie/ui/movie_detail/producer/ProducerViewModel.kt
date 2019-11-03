package com.canh.movie.ui.movie_detail.producer

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.data.model.Company
import com.canh.movie.ui.base.BaseViewModel

class ProducerViewModel : BaseViewModel() {
    private val _companies = MutableLiveData<List<Company>>()

    val companies: LiveData<List<Company>> = _companies
    var isAllLoadedObservable = ObservableBoolean(false)

    override fun onCreate() {

    }

    fun postCompanies(companies: List<Company>){
        _companies.postValue(companies)
        isAllLoadedObservable.set(true)
    }

}
