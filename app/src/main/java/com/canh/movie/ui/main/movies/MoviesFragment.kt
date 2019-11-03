package com.canh.movie.ui.main.movies

import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.Genres
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.FragmentMoviesBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.main.MainActivity
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(),
    BaseAdapterItemClickListener<Movie> {

    override val viewModel: MoviesViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_movies

    override fun initComponents() {
        movieListener = this
        setToolbarTitleByCategory()
        setToolbarTitleByGenres()
    }

    override fun onStart() {
        super.onStart()
        getMoviesByCategory()
        getMoviesByGenres()
    }

    override fun onItemClick(item: Movie) {
        startActivity(
            MovieDetailActivity.getIntent(
                activity!!,
                item.id,
                item.title
            )
        )
    }

    private fun getMoviesByCategory() {
        val categoryQuery = arguments?.getString(ARGUMENT_CATEGORY_QUERY)
        categoryQuery?.let { viewModel.getMoviesByCategoryType(it) }
    }

    private fun setToolbarTitleByCategory() {
        val categoryTitle = arguments?.getString(ARGUMENT_CATEGORY_TITLE)
        categoryTitle?.let {
            (activity as MainActivity).supportActionBar?.title = it
        }
    }

    private fun getMoviesByGenres() {
        val genres: Genres? = arguments?.getParcelable(ARGUMENT_GENRES)
        genres?.let { viewModel.getMoviesByGenres(it.id) }
    }

    private fun setToolbarTitleByGenres() {
        val genres: Genres? = arguments?.getParcelable(ARGUMENT_GENRES)
        genres?.let {
            (activity as MainActivity).supportActionBar?.title = it.name
        }
    }

    companion object {
        private const val ARGUMENT_CATEGORY_QUERY = "ARGUMENT_CATEGORY_QUERY"
        private const val ARGUMENT_CATEGORY_TITLE = "ARGUMENT_CATEGORY_TITLE"
        private const val ARGUMENT_GENRES = "ARGUMENT_GENRES"

        @JvmStatic
        lateinit var movieListener: BaseAdapterItemClickListener<Movie>

        fun newInstance(
            @CategoryQuery categoryQuery: String, categoryTitle: String
        ) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_CATEGORY_QUERY, categoryQuery)
                    putString(ARGUMENT_CATEGORY_TITLE, categoryTitle)
                }
            }

        fun newInstance(genres: Genres) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENT_GENRES, genres)
                }
            }
    }
}
