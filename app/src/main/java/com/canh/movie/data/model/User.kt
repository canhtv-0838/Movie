package com.canh.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("id")
    @Expose
    var id: Long,
    @SerializedName("fullname")
    @Expose
    var fullname: String,
    @SerializedName("birthday")
    @Expose
    var birthday: String,
    @SerializedName("gender")
    @Expose
    var gender: Int,
    @SerializedName("place_of_birth")
    @Expose
    var placeOfBirth: String,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String? = null
) : Parcelable
