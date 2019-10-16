package com.canh.movie.ui.base

interface BaseItemClickListener<T> : BaseAdapter.OnItemClickListener {
    fun onClick(item: T)
}