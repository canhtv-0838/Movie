package com.canh.movie.ui.main.profile

import com.canh.movie.data.model.SharedPost
import com.canh.movie.ui.base.BaseAdapterItemClickListener

interface ProfileListener : BaseAdapterItemClickListener<Any>{
    fun onMovieClick(idMovie: Int, titleMovie: String)
    fun deleteSharedPost(item: SharedPost)
}
