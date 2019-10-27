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
    annotation class CategoryName {
    companion object {
        const val TITLE_POPULAR = "Popular"
        const val TITLE_NOW_PLAYING = "Now Playing"
        const val TITLE_UPCOMING = "Upcomming"
        const val TITLE_TOP_RATED = "Top Rate"
    }
}
