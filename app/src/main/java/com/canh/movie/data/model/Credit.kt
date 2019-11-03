package com.canh.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credit(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("cast")
    @Expose
    val casts: List<Cast> = emptyList()
) : Parcelable