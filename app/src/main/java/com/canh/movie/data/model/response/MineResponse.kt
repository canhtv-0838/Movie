package com.canh.movie.data.model.response

import com.google.gson.annotations.SerializedName

data class MineResponse(
    @SerializedName("error_result")
    val resultCode: Int,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("data")
    val data: Any,
    @SerializedName("message")
    val message: String? = null
)
