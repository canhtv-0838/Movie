package com.canh.movie.ui.main.movies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.canh.movie.R
import com.canh.movie.data.model.CategoryQuery
import com.canh.movie.data.model.Genres
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.FragmentMoviesBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.main.MainActivity
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import com.canh.movie.utils.log
import kotlinx.android.synthetic.main.fragment_movies.*
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

    override fun observeData() {
        super.observeData()
        getMoviesByCategory(1)
        getMoviesByGenres(1)

        viewModel.pageLoadingByCategory.observe(this, Observer {
            it?.let { currentPage ->
                onPageChange(currentPage, viewModel.totalPageLoadingByCategory.value!!)
            }
        })

        viewModel.pageLoadingByGenre.observe(this, Observer {
            it?.let { currentPage ->
                onPageChange(currentPage, viewModel.totalPageLoadingByGenre.value!!)
            }
        })


        homeRecyclerMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
                        movieLinearPage.visibility = View.VISIBLE
                    } else {
                        movieLinearPage.visibility = View.GONE
                    }
                }
            }
        })

        movieButtonPrev.setOnClickListener {
            getMoviesByCategory(Integer.parseInt(movieTextPage.text.toString()) - 1)
            getMoviesByGenres(Integer.parseInt(movieTextPage.text.toString()) - 1)
        }

        movieButtonNext.setOnClickListener {
            getMoviesByCategory(Integer.parseInt(movieTextPage.text.toString()) + 1)
            getMoviesByGenres(Integer.parseInt(movieTextPage.text.toString()) + 1)
        }
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

    private fun getMoviesByCategory(page: Int) {
        val categoryQuery = arguments?.getString(ARGUMENT_CATEGORY_QUERY)
        categoryQuery?.let { viewModel.getMoviesByCategoryType(it, page) }
    }

    private fun setToolbarTitleByCategory() {
        val categoryTitle = arguments?.getString(ARGUMENT_CATEGORY_TITLE)
        categoryTitle?.let {
            (activity as MainActivity).supportActionBar?.title = it
        }
    }

    private fun getMoviesByGenres(page: Int) {
        val genres: Genres? = arguments?.getParcelable(ARGUMENT_GENRES)
        genres?.let { viewModel.getMoviesByGenres(it.id, page) }
    }

    private fun setToolbarTitleByGenres() {
        val genres: Genres? = arguments?.getParcelable(ARGUMENT_GENRES)
        genres?.let {
            (activity as MainActivity).supportActionBar?.title = it.name
        }
    }

    private fun onPageChange(currentPage : Int, totalPage: Int){
        movieTextPage.text = currentPage.toString()
        if (currentPage == 1 && currentPage < totalPage) {
            movieButtonPrev.isEnabled = false
            movieButtonNext.isEnabled = true
        } else if (currentPage in 2 until totalPage) {
            movieButtonPrev.isEnabled = true
            movieButtonNext.isEnabled = true
        } else if (currentPage > 1 && currentPage == totalPage) {
            movieButtonPrev.isEnabled = true
            movieButtonNext.isEnabled = false
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
