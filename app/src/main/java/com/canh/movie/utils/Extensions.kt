package com.canh.movie.utils

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun log(msg : String) : Int
= Log.d("canh123", msg)

fun hideKeyboard(activity: AppCompatActivity) {
    val view = activity.currentFocus?: View(activity)
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
