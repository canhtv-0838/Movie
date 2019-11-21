package com.canh.movie.data.source.remote

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.coroutines.awaitResult
import com.canh.movie.data.model.response.MineResponse
import com.canh.movie.data.model.result.MineResult
import com.canh.movie.data.source.MineDataSource
import com.canh.movie.network.mine.MineApiRequest

class MineRemoteDataSource(private val mineApi: MineApiRequest) : MineDataSource.Remote {

    override suspend fun loginAccount(
        email: String,
        password: String
    ): CoroutinesResult<MineResponse> {
        return mineApi.loginAccountAsync(email, password).awaitResult()
    }

    override suspend fun registerAccount(
        fullname: String,
        birthday: String,
        gender: Int,
        placeOfBirth: String,
        username: String,
        password: String,
        email: String,
        idUser: Long
    ): CoroutinesResult<MineResult> {
        return mineApi.registerAccountAsync(
            fullname,
            birthday,
            gender,
            placeOfBirth,
            username,
            password,
            email,
            idUser
        )
            .awaitResult()
    }

    override suspend fun getDetailAccount(idUser: Long): CoroutinesResult<MineResponse> {
        return mineApi.getDetailAccountAsync(idUser).awaitResult()
    }

    override suspend fun sharePost(
        idUser: Long,
        idMovie: Int,
        titleMovie: String,
        overviewMovie: String,
        posterPathMovie: String,
        content: String
    ): CoroutinesResult<MineResult> {
        return mineApi.sharePostAsync(idUser, idMovie, titleMovie, overviewMovie, posterPathMovie, content).awaitResult()
    }

    override suspend fun getAllSharedPost(): CoroutinesResult<MineResponse> {
        return mineApi.getAllSharedPostAsync().awaitResult()
    }

    override suspend fun getSharedPostFollowUser(idUser: Long): CoroutinesResult<MineResponse> {
        return mineApi.getSharedPostFollowUserAsync(idUser).awaitResult()
    }

    override suspend fun deleteSharedPost(id: Long): CoroutinesResult<MineResult> {
        return mineApi.deleteSharedPostAsync(id).awaitResult()
    }
}
