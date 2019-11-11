package com.canh.movie.ui.cast_detail.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.data.model.People
import com.canh.movie.ui.base.BaseViewModel

class CastInformationViewModel : BaseViewModel(){
    private val _person = MutableLiveData<People>()

    val person: LiveData<People> = _person

    override fun onCreate() {

    }

    fun postPerson(people: People) {
        _person.postValue(people)
    }

}
