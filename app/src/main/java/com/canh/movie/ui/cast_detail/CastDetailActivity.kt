package com.canh.movie.ui.cast_detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.data.model.People
import com.canh.movie.databinding.ActivityCastDetailBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.cast_detail.information.CastInformationFragment
import com.canh.movie.ui.cast_detail.movies.CastMoviesFragment
import com.canh.movie.ui.movie_detail.information.InformationFragment
import kotlinx.android.synthetic.main.activity_cast_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class CastDetailActivity : BaseActivity<ActivityCastDetailBinding, CastDetailViewModel>() {

    private lateinit var pagerAdapter: CastDetailPagerAdapter

    override val viewModel: CastDetailViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_cast_detail

    override fun initComponents() {
        setToolbar()
        setBackDropView()

        getCastDetail()
    }

    override fun observeData() {
        viewModel.person.observe(this, Observer {
            it?.let {
                setToolbarTitle(it.name)
                initPagerAdapter(it)
            }
        })
    }

    override fun getContainerId(): Int = 0

    private fun getCastDetail() {
        intent?.getIntExtra(EXTRA_PERSON_ID, 0)?.let { personId ->
            viewModel.getCastDetail(personId)
        }
    }

    private fun initPagerAdapter(people: People) {
        pagerAdapter = CastDetailPagerAdapter(supportFragmentManager)
        castDetailViewPager?.adapter = pagerAdapter
        pagerAdapter.addFragments(CastInformationFragment.newInstance(people))
        pagerAdapter.addFragments(CastMoviesFragment.newInstance(people))
        castDetailTabLayout?.setupWithViewPager(castDetailViewPager, true)
    }

    private fun setToolbar() {
        setSupportActionBar(castDetailToolBar)
    }

    private fun setToolbarTitle(name: String) {
        supportActionBar?.title = name
    }

    private fun setBackDropView() {
        castDetailToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        castDetailToolBar.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        private const val EXTRA_PERSON_ID = "EXTRA_PERSON_ID"

        fun getIntent(context: Context, personId: Int) =
            Intent(context, CastDetailActivity::class.java).apply {
                putExtra(EXTRA_PERSON_ID, personId)
            }
    }
}
