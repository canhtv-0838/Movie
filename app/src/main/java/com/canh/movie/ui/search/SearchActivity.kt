package com.canh.movie.ui.search

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.ActivitySearchBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import com.canh.movie.utils.hideKeyboard
import com.canh.movie.utils.log
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(),
    BaseAdapterItemClickListener<Movie>, TextView.OnEditorActionListener {

    override val viewModel: SearchViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_search

    override fun initComponents() {
        setEvent()
    }

    override fun observeData() {
        //TODO To change body of created functions use File | Settings | File Templates.
    }

    override fun getContainerId(): Int = 0

    override fun onItemClick(item: Movie) {
        log("${item.id}")
        startActivity(MovieDetailActivity.getIntent(this, item.id, item.title))
    }

    override fun onEditorAction(textView: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            viewModel.searchMovies(textView?.text.toString())
            hideKeyboard(this)
            return true
        }
        return false
    }

    private fun setEvent() {
        listener = this
        searchButtonBack.setOnClickListener {
            onBackPressed()
        }

        searchTextSearch.requestFocus()
        searchTextSearch.setOnEditorActionListener(this)
    }

    companion object {

        @JvmStatic
        lateinit var listener: BaseAdapterItemClickListener<Movie>
    }
}
