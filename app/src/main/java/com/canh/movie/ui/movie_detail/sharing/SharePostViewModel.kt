package com.canh.movie.ui.movie_detail.sharing

import androidx.lifecycle.MutableLiveData
import com.canh.movie.coroutines.getData
import com.canh.movie.data.repository.MineRepository
import com.canh.movie.network.mine.ERROR_CODE_SUCCESS
import com.canh.movie.network.mine.RESULT_CODE_SUCCESS
import com.canh.movie.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharePostViewModel(private val mineRepository: MineRepository) : BaseViewModel() {
    val SHARE_SUCCESS = "SHARE_SUCCESS"
    val SHARE_FAILED = "SHARE_FAILED"

    val idUser = MutableLiveData<Long>()
    val idMovie = MutableLiveData<Int>()
    val posterPathMovie = MutableLiveData<String>()
    val overviewMovie = MutableLiveData<String>()
    val titleMovie = MutableLiveData<String>()

    var shareStatus = MutableLiveData<String>()
    override fun onCreate() {

    }

    fun sharePost(
        idUser: Long,
        idMovie: Int,
        titleMovie: String,
        overviewMovie: String,
        posterPathMovie: String,
        content: String
    ) =
        launch(Dispatchers.IO) {
            mineRepository.sharePost(
                idUser,
                idMovie,
                titleMovie,
                overviewMovie,
                posterPathMovie,
                content
            ).getData(
                onSuccess = {
                    if (it.resultCode == RESULT_CODE_SUCCESS) {
                        if (it.errorCode == ERROR_CODE_SUCCESS) {
                            shareStatus.postValue(SHARE_SUCCESS)
                        }
                    }
                },
                onFailed = {
                    shareStatus.postValue(SHARE_FAILED)
                }
            )
        }

}
