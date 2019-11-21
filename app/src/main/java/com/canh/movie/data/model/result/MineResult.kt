package com.canh.movie.data.model.result

import com.google.gson.annotations.SerializedName

data class MineResult(
    @SerializedName("error_result")
    val resultCode: Int,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("message")
    val message: String? = null
)
