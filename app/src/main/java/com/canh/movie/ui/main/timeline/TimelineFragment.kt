package com.canh.movie.ui.main.timeline

import com.canh.movie.R
import com.canh.movie.databinding.FragmentTimelineBinding
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.main.profile.ProfileFragment
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import com.canh.movie.utils.log
import org.koin.android.viewmodel.ext.android.viewModel

class TimelineFragment : BaseFragment<FragmentTimelineBinding, TimelineViewModel>(),
    TimelineListener {

    override val viewModel: TimelineViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_timeline

    override fun initComponents() {
        listener = this
    }

    override fun onItemClick(item: Any) {
        //do nothing
    }

    override fun onMovieClick(idMovie: Int, titleMovie: String) {
        startActivity(MovieDetailActivity.getIntent(activity!!, idMovie, titleMovie))
    }

    override fun onUserClick(idUser: Long) {
        log("$idUser")
        addFragment(ProfileFragment.newInstance(idUser), true)
    }

    companion object {
        @JvmStatic
        lateinit var listener: TimelineListener

        fun newInstance() = TimelineFragment()
    }
}
