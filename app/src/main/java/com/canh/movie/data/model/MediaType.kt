package com.canh.movie.data.model

import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
@StringDef(
    MediaType.ALL,
    MediaType.MOVIE,
    MediaType.TV,
    MediaType.PERSON
)
annotation class MediaType {
    companion object {
        const val ALL = "all"
        const val MOVIE = "movie"
        const val TV = "tv"
        const val PERSON = "person"
    }
}
