package com.canh.movie.ui.search

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.ActivitySearchBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.base.BaseAdapter
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import com.canh.movie.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(),
    BaseAdapterItemClickListener<Movie>, TextView.OnEditorActionListener {

    private var oldTextSearch = ""

    private lateinit var adapter: BaseAdapter<Movie>

    override val viewModel: SearchViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_search

    override fun initComponents() {
        initAdapter()
        setEvent()
    }

    override fun observeData() {
        viewModel.movies.observe(this, Observer {
            it?.let {
                if (adapter.itemCount == 0) {
                    adapter.setItems(it)
                } else {
                    adapter.addItems(it)
                }
            }
        })

        searchRecyclerMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (adapter.itemCount > 0) {
                            viewModel.searchMovies(searchTextSearch.text.toString())
                        }
                        viewModel.isAllLoadedObservable.set(false)
                    }
                }
            }
        })
    }

    override fun getContainerId(): Int = 0

    override fun onItemClick(item: Movie) {
        startActivity(MovieDetailActivity.getIntent(this, item.id, item.title))
    }

    override fun onEditorAction(textView: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (oldTextSearch != textView?.text.toString()) {
                viewModel.tempMovies.clear()
                viewModel.pageLoading = 1
            }
            viewModel.searchMovies(textView?.text.toString())
            oldTextSearch = textView?.text.toString()
            hideKeyboard(this)
            return true
        }
        return false
    }

    private fun setEvent() {
        searchButtonBack.setOnClickListener {
            onBackPressed()
        }

        searchTextSearch.requestFocus()
        searchTextSearch.setOnEditorActionListener(this)
    }

    private fun initAdapter() {
        adapter = BaseAdapter(R.layout.item_movie_movies)
        adapter.setListener(this)
        searchRecyclerMovies.adapter = adapter
    }
}
