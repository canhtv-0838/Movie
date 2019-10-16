package com.canh.movie.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) =
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment).apply {
            if (addToBackStack) addToBackStack(null)
        }.commit()

fun AppCompatActivity.addFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) = supportFragmentManager.beginTransaction()
    .add(containerId, fragment).apply {
        if (addToBackStack) addToBackStack(null)
    }.commit()
