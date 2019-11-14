package com.canh.movie.ui.search

import com.canh.movie.R
import com.canh.movie.databinding.ActivitySearchBinding
import com.canh.movie.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(){
    override val viewModel: SearchViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_search

    override fun initComponents() {
        setEvent()
    }

    override fun observeData() {
        //TODO To change body of created functions use File | Settings | File Templates.
    }

    override fun getContainerId(): Int = 0

    private fun setEvent(){
        searchTextSearch.requestFocus()
        searchButtonBack.setOnClickListener {
            onBackPressed()
        }
    }
}
