package com.canh.movie.ui.cast_detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.canh.movie.R

class CastDetailPagerAdapter(fragmentManager: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.cast_detail_title_information)
            1 -> context.getString(R.string.cast_detail_title_movies)
            else -> null
        }
    }

    fun addFragments(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }
}
