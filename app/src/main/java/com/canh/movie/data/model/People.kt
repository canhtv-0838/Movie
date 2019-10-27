package com.canh.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("birthday")
    @Expose
    val birthday: String? = null,
    @SerializedName("known_for_department")
    @Expose
    val knownForDepartment: String,
    @SerializedName("deathday")
    @Expose
    val deathday: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("also_known_as")
    @Expose
    val alsoKnownAs: List<String>,
    @SerializedName("gender")
    @Expose
    val gender: Int,
    @SerializedName("biography")
    @Expose
    val biography: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("place_of_birth")
    @Expose
    val placeOfBirth: String? = null,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String? = null,
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @SerializedName("homepage")
    @Expose
    val homePage: String? = null
)
