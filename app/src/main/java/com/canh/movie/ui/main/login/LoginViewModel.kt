package com.canh.movie.ui.main.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.User
import com.canh.movie.data.repository.MineRepository
import com.canh.movie.network.mine.ERROR_CODE_SUCCESS
import com.canh.movie.network.mine.RESULT_CODE_SUCCESS
import com.canh.movie.ui.base.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val mineRepository: MineRepository) :
    BaseViewModel() {

    val LOGIN_SUCCESS = "LOGIN_SUCCESS"
    val LOGIN_FAILED = "LOGIN_FAILED"

    var loginStatus = MutableLiveData<String>()
    var idUser = MutableLiveData<Long>()


    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    override fun onCreate() {

    }

    fun loginAccount(email: String, password: String) = launch(Dispatchers.IO) {
        mineRepository.loginAccount(email, password).getData(
            onSuccess = {
                if (it.resultCode == RESULT_CODE_SUCCESS) {
                    when (it.errorCode) {
                        ERROR_CODE_SUCCESS -> {
                            loginStatus.postValue(LOGIN_SUCCESS)

                            getUserDetail(it.data.toString().toLong())
                            idUser.postValue(it.data.toString().toLong())
                        }
                        else -> loginStatus.postValue(LOGIN_FAILED)
                    }
                }
            },
            onFailed = {
                loginStatus.postValue(LOGIN_FAILED)
            }
        )
    }

    private fun getUserDetail(idUser: Long) = launch(Dispatchers.IO) {
        mineRepository.getDetailAccount(idUser).getData(
            onSuccess = { mineResponse ->
                val userResponse = Gson().fromJson(mineResponse.data.toString(),User::class.java)
                _user.postValue(userResponse)
            },
            onFailed = {
                messageNotification.postValue(it.toString())
            }
        )
    }
}
