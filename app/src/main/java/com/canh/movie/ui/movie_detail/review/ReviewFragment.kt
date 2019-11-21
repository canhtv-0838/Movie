package com.canh.movie.ui.movie_detail.review

import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.Review
import com.canh.movie.databinding.FragmentReviewBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.utils.log
import org.koin.android.viewmodel.ext.android.viewModel

class ReviewFragment : BaseFragment<FragmentReviewBinding, ReviewViewModel>(),
    BaseAdapterItemClickListener<Review> {
    override fun onItemClick(item: Review) {
        log("${item.author}")
    }

    override val viewModel: ReviewViewModel by viewModel()
    override fun getLayoutResource(): Int = R.layout.fragment_review

    override fun initComponents() {
        listener = this
    }

    override fun initData() {
        super.initData()
        arguments?.getInt(ARGUMENT_MOVIE_ID)?.let {
            viewModel.getMovieReviews(it)
        }
    }
    companion object {
        lateinit var listener : BaseAdapterItemClickListener<Review>
        private const val ARGUMENT_MOVIE_ID = "ARGUMENT_MOVIE_ID"

        fun newInstance(movieId: Int) = ReviewFragment().apply {
            arguments = Bundle().apply {
                putInt(ARGUMENT_MOVIE_ID, movieId)
            }
        }
    }
}
