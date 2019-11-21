package com.canh.movie.ui.main.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.model.SharedPost
import com.canh.movie.data.repository.MineRepository
import com.canh.movie.network.mine.ERROR_CODE_SUCCESS
import com.canh.movie.network.mine.RESULT_CODE_SUCCESS
import com.canh.movie.ui.base.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class TimelineViewModel(private val mineRepository: MineRepository) : BaseViewModel() {
    private val _sharedPosts = MutableLiveData<List<SharedPost>>()
    val sharedPosts: LiveData<List<SharedPost>> = _sharedPosts

    override fun onCreate() {
        getAllSharedPost()
    }

    fun getAllSharedPost() = launch(Dispatchers.IO) {
        mineRepository.getAllSharedPost().getData(
            onSuccess = {
                if (it.resultCode == RESULT_CODE_SUCCESS) {
                    if (it.errorCode == ERROR_CODE_SUCCESS) {
                        val jsonArray = JSONArray(it.data.toString())
                        val arrData = ArrayList<SharedPost>()
                        for (i in 0 until jsonArray.length()) {
                            arrData.add(
                                Gson().fromJson(
                                    jsonArray.getJSONObject(i).toString(),
                                    SharedPost::class.java
                                )
                            )
                        }
                        arrData.sortByDescending { sharePost ->
                            sharePost.id
                        }
                        _sharedPosts.postValue(arrData)
                    }
                }
            },
            onFailed = {
                //todo
            }
        )
    }

}
