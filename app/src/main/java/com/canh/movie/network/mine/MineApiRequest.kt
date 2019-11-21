package com.canh.movie.network.mine

import com.canh.movie.data.model.response.MineResponse
import com.canh.movie.data.model.result.MineResult
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

const val MINE_DB_BASE_URL = "http://192.168.0.73:80/movie_db/"

const val RESULT_CODE_SUCCESS = 0
const val RESULT_CODE_FAILED = 1

const val ERROR_CODE_SUCCESS = 0

interface MineApiRequest {
    @FormUrlEncoded
    @POST("register_account.php")
    fun registerAccountAsync(
        @Field("fullname") fullname: String,
        @Field("birthday") birthday: String,
        @Field("gender") gender: Int,
        @Field("place_of_birth") placeOfBirth: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("id_user") idUser: Long
    ): Deferred<MineResult>

    @FormUrlEncoded
    @POST("login_account.php")
    fun loginAccountAsync(
        @Field("email") email: String,
        @Field("password") password: String
    ): Deferred<MineResponse>

    @FormUrlEncoded
    @POST("get_user_detail.php")
    fun getDetailAccountAsync(@Field("id_user") idUser: Long): Deferred<MineResponse>

    @FormUrlEncoded
    @POST("share_post.php")
    fun sharePostAsync(
        @Field("id_user") idUser: Long,
        @Field("id_movie") idMovie: Int,
        @Field("title_movie") titleMovie: String,
        @Field("overview_movie") overviewMovie: String,
        @Field("poster_path_movie") posterPathMovie: String,
        @Field("content") content: String
    ): Deferred<MineResult>

    @GET("get_all_shared_post.php")
    fun getAllSharedPostAsync(): Deferred<MineResponse>

    @FormUrlEncoded
    @POST("get_shared_post_follow_user.php")
    fun getSharedPostFollowUserAsync(
        @Field("id_user") idUser: Long
    ): Deferred<MineResponse>

    @FormUrlEncoded
    @POST("delete_shared_post.php")
    fun deleteSharedPostAsync(
        @Field("id") id: Long
    ): Deferred<MineResult>
}
