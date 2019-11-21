package com.canh.movie.utils

import android.annotation.SuppressLint
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object StringUtils {
    fun getSmallImage(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
            .append(Constants.IMAGE_SIZE_W200)
            .append(image_path)
        return builder.toString()
    }

    fun getSmallImageFromMine(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH_MINE)
            .append(image_path)
        return builder.toString()
    }

    fun getImage(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
            .append(Constants.IMAGE_SIZE_W500)
            .append(image_path)
        return builder.toString()
    }

    fun concateString(vararg strings: String): String {
        val stringBuilder = StringBuilder()
        for (s in strings) {
            stringBuilder.append(s)
        }
        return stringBuilder.toString()
    }

    fun getThumbnail(trailerKey: String): String {
        return String.format(Constants.BASE_THUMBNAIL_PATH, trailerKey)
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormated(date: Date): String {
        val format = "dd/MM/yyyy"
        return SimpleDateFormat(format).format(date)
    }

    fun isEmail(editText: EditText): Boolean {
        val isEmail: Boolean
        val p: Pattern
        val m: Matcher
        val EMAIL_STRING =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        p = Pattern.compile(EMAIL_STRING)
        m = p.matcher(editText.getText().toString())
        isEmail = m.matches()
        if (!isEmail) {
            editText.error = "Not valid email!"
        }
        return isEmail
    }

    fun isEnoughLength(vararg editTexts: EditText, minlength: Int, maxLength: Int): Boolean {
        var isEnough = true
        for (editText in editTexts) {
            if (editText.getText().toString().length < minlength) {
                isEnough = false
                editText.error = "You must input at least $minlength characters!"
            } else if (editText.getText().toString().length > maxLength) {
                isEnough = false
                editText.error = "You only can input maximum $maxLength characters!"
            }
        }
        return isEnough
    }

    fun isMaxLength(vararg editTexts: EditText, maxLength: Int): Boolean {
        var isMax = false
        for (editText in editTexts) {
            if (editText.text.toString().length > maxLength) {
                isMax = true
                editText.error = "You only can input maximum $maxLength characters!"
            }
        }
        return isMax
    }
}
