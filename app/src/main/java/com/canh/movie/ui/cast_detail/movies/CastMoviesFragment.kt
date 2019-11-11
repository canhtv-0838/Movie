package com.canh.movie.ui.cast_detail.movies

import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.data.model.People
import com.canh.movie.databinding.FragmentCastMoviesBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class CastMoviesFragment : BaseFragment<FragmentCastMoviesBinding, CastMoviesViewModel>(),
    BaseAdapterItemClickListener<Movie> {

    override val viewModel: CastMoviesViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_cast_movies

    override fun initComponents() {
        listener = this
    }

    override fun initData() {
        super.initData()
        arguments?.getParcelable<People>(ARGUMENT_PEOPLE)?.let {
            viewModel.getMoviesByCast(it.id)
        }
    }

    override fun onItemClick(item: Movie) {
        startActivity(MovieDetailActivity.getIntent(activity!!, item.id, item.title))
    }

    companion object {
        private const val ARGUMENT_PEOPLE = "ARGUMENT_PEOPLE"
        lateinit var listener: BaseAdapterItemClickListener<Movie>

        fun newInstance(people: People) = CastMoviesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_PEOPLE, people)
            }
        }
    }
}
