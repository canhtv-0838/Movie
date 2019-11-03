package com.canh.movie.ui.movie_detail.information

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.FragmentInformationBinding
import com.canh.movie.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_information.*
import org.koin.android.viewmodel.ext.android.viewModel

class InformationFragment : BaseFragment<FragmentInformationBinding, InformationViewModel>() {
    override val viewModel: InformationViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_information

    override fun initComponents() {
        inforTextOverview.movementMethod = ScrollingMovementMethod()
    }

    override fun initData() {
        super.initData()
        arguments?.getParcelable<Movie>(ARGUMENT_MOVIE)?.let {
            viewModel.postVideoResult(it)
        }
    }

    companion object {
        private const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        @JvmStatic
        fun newInstance(movie: Movie) = InformationFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_MOVIE, movie)
            }
        }
    }
}