package com.canh.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    @SerializedName("cast_id")
    @Expose
    val castId: Int,
    @SerializedName("character")
    @Expose
    val character: String,
    @SerializedName("credit_id")
    @Expose
    val creditId: String,
    @SerializedName("gender")
    @Expose
    val gender: Int? = null,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("order")
    @Expose
    val order: Int,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String? = null
) : Parcelable
