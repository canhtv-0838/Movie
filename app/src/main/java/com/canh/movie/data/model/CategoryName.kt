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
        const val POPULAR = "Popular"
        const val NOW_PLAYING = "Now playing"
        const val UPCOMING = "Upcoming"
        const val TOP_RATED = "Top rate"
    }
}
