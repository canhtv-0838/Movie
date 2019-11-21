package com.canh.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SharedPost(
    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("id_user")
    @Expose
    var idUser: Long,
    @SerializedName("fullname")
    @Expose
    var fullnameUser: String,
    @SerializedName("id_movie")
    @Expose
    var idMovie: Int,
    @SerializedName("title_movie")
    @Expose
    var titleMovie: String? = null,
    @SerializedName("overview_movie")
    @Expose
    var overviewMovie: String? = null,
    @SerializedName("poster_path_movie")
    @Expose
    var posterPathMovie: String? = null,
    @SerializedName("profile_path_user")
    @Expose
    var profilePathUser: String? = null,
    @SerializedName("content")
    @Expose
    var content: String? = null,
    @SerializedName("post_time")
    @Expose
    var postTime: String
) : Parcelable
