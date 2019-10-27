package com.canh.movie.utils

object StringUtils {
    fun getSmallImage(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
            .append(Constants.IMAGE_SIZE_W200)
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
}
