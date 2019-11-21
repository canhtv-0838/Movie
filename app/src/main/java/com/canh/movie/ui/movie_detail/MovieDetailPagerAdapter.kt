package com.canh.movie.ui.movie_detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.canh.movie.R

class MovieDetailPagerAdapter(fragmentManager: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.movie_detail_title_information)
            1 -> context.getString(R.string.movie_detail_title_trailer)
            2 -> context.getString(R.string.movie_detail_title_reviews)
            3 -> context.getString(R.string.movie_detail_title_cast)
            4 -> context.getString(R.string.movie_detail_title_producer)
            else -> null
        }
    }

    fun addFragments(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }
}
