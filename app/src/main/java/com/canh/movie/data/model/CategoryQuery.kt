package com.canh.movie.data.model

import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
@StringDef(
    CategoryQuery.POPULAR,
    CategoryQuery.NOW_PLAYING,
    CategoryQuery.UPCOMING,
    CategoryQuery.TOP_RATED
)
annotation class CategoryQuery {
    companion object {
        const val POPULAR = "popular"
        const val NOW_PLAYING = "now_playing"
        const val UPCOMING = "upcoming"
        const val TOP_RATED = "top_rated"
    }
}
