package com.canh.movie.ui.main.timeline

import com.canh.movie.ui.base.BaseAdapterItemClickListener

interface TimelineListener : BaseAdapterItemClickListener<Any>{
    fun onUserClick(idUser: Long)
    fun onMovieClick(idMovie: Int, titleMovie: String)
}
