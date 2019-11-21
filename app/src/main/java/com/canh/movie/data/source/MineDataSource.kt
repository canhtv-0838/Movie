package com.canh.movie.data.source

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.data.model.response.MineResponse
import com.canh.movie.data.model.result.MineResult

interface MineDataSource {
    interface Remote {
        suspend fun registerAccount(
            fullname: String,
            birthday: String,
            gender: Int,
            placeOfBirth: String,
            username: String,
            password: String,
            email: String,
            idUser: Long
        ): CoroutinesResult<MineResult>

        suspend fun loginAccount(
            email: String,
            password: String
        ): CoroutinesResult<MineResponse>

        suspend fun getDetailAccount(idUser: Long): CoroutinesResult<MineResponse>

        suspend fun sharePost(
            idUser: Long,
            idMovie: Int,
            titleMovie: String,
            overviewMovie: String,
            posterPathMovie: String,
            content: String
        ): CoroutinesResult<MineResult>

        suspend fun getAllSharedPost(): CoroutinesResult<MineResponse>

        suspend fun getSharedPostFollowUser(idUser: Long): CoroutinesResult<MineResponse>

        suspend fun deleteSharedPost(id: Long): CoroutinesResult<MineResult>
    }
}
