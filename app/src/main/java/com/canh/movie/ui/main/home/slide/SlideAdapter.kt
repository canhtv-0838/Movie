package com.canh.movie.ui.main.home.slide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.ItemSlideHomeBinding

class SlideAdapter : PagerAdapter(), View.OnClickListener {

    private var movies: List<Movie> = emptyList()
    private var listener: SlideListener? = null
    private var binding: ItemSlideHomeBinding? = null

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = movies.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(container.context),
            R.layout.item_slide_home,
            container,
            true
        )
        binding?.apply {
            item = movies[position]
            homeImageSlide?.setOnClickListener(this@SlideAdapter)
            executePendingBindings()
        }
        return binding?.root!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val view = obj as View
        container.removeView(view)
    }

    override fun onClick(v: View?) {
        v?.let {
            listener?.onSlideClick(movies)
        }
    }

    fun setItem(listMovies: List<Movie>) {
        movies = listMovies
        notifyDataSetChanged()
    }

    fun setListener(slideListener: SlideListener) {
        listener = slideListener
    }

    interface SlideListener {
        fun onSlideClick(movies: List<Movie>)
    }
}
