package com.canh.movie.data.repository

import com.canh.movie.coroutines.CoroutinesResult
import com.canh.movie.data.model.response.MineResponse
import com.canh.movie.data.model.result.MineResult
import com.canh.movie.data.source.MineDataSource

class MineRepository(private val remote: MineDataSource.Remote) :
    MineDataSource.Remote {

    override suspend fun loginAccount(
        email: String,
        password: String
    ): CoroutinesResult<MineResponse> {
        return remote.loginAccount(email, password)
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
        return remote.registerAccount(
            fullname,
            birthday,
            gender,
            placeOfBirth,
            username,
            password,
            email,
            idUser
        )
    }

    override suspend fun getDetailAccount(idUser: Long): CoroutinesResult<MineResponse> {
        return remote.getDetailAccount(idUser)
    }

    override suspend fun sharePost(
        idUser: Long,
        idMovie: Int,
        titleMovie: String,
        overviewMovie: String,
        posterPathMovie: String,
        content: String
    ): CoroutinesResult<MineResult> {
        return remote.sharePost(idUser, idMovie, titleMovie, overviewMovie, posterPathMovie, content)
    }

    override suspend fun getAllSharedPost(): CoroutinesResult<MineResponse> {
        return remote.getAllSharedPost()
    }

    override suspend fun getSharedPostFollowUser(idUser: Long): CoroutinesResult<MineResponse> {
        return remote.getSharedPostFollowUser(idUser)
    }

    override suspend fun deleteSharedPost(id: Long): CoroutinesResult<MineResult> {
        return remote.deleteSharedPost(id)
    }
}
