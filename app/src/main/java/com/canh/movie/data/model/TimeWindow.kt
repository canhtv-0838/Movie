package com.canh.movie.data.model

import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
@StringDef(
    TimeWindow.DAY,
    TimeWindow.WEEK
)

annotation class TimeWindow {
    companion object {
        const val DAY = "day"
        const val WEEK = "week"
    }
}
