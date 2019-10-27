package com.canh.movie.ui.base

interface BaseAdapterItemClickListener<T> : BaseAdapter.OnItemClickListener {
    fun onItemClick(item: T)
}
