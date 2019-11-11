package com.canh.movie.ui.cast_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CastDetailPagerAdapter (fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private var fragments:  MutableList<Fragment> = mutableListOf()

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Information"
                1 -> "Movies"
                else -> null
            }
        }

        fun addFragments(fragment: Fragment) {
            fragments.add(fragment)
            notifyDataSetChanged()
        }
}
