package com.canh.movie.ui.main.register

import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.repository.MineRepository
import com.canh.movie.network.mine.ERROR_CODE_SUCCESS
import com.canh.movie.network.mine.RESULT_CODE_SUCCESS
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel(private val mineRepository: MineRepository) : BaseViewModel() {
    val REGISTER_SUCCESS = "REGISTER_SUCCESS"
    val REGISTER_FAILED = "REGISTER_FAILED"

    var registerStatus = MutableLiveData<String>()

    override fun onCreate() {

    }

    fun registerAccount(
        fullname: String,
        birthday: String,
        gender: Int,
        placeOfBirth: String,
        username: String,
        password: String,
        email: String,
        idUser: Long
    ) = launch(Dispatchers.IO) {
        mineRepository.registerAccount(
            fullname,
            birthday,
            gender,
            placeOfBirth,
            username,
            password,
            email,
            idUser
        ).getData(
            onSuccess = {
                if (it.resultCode == RESULT_CODE_SUCCESS) {
                    when (it.errorCode) {
                        ERROR_CODE_SUCCESS -> registerStatus.postValue(REGISTER_SUCCESS)
                        else -> {
                        }//todo
                    }
                }
            },
            onFailed = {
                registerStatus.postValue(REGISTER_FAILED)
            }
        )
    }
}
