package com.canh.movie.ui.movie_detail

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.ActivityMovieDetailBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.movie_detail.cast.CastFragment
import com.canh.movie.ui.movie_detail.information.InformationFragment
import com.canh.movie.ui.movie_detail.producer.ProducerFragment
import com.canh.movie.ui.movie_detail.trailer.TrailerFragment
import com.canh.movie.utils.log
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>(),
    MovieTrailerListener {

    private lateinit var youtubeFragment: YoutubeFragment
    private lateinit var pagerAdapter: MovieDetailPagerAdapter

    override val viewModel: MovieDetailViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_movie_detail

    override fun getContainerId(): Int = 0

    override fun initComponents() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            setTextStatusBarColor()
        }

        setToolbar()
        setBackDropView()

        initYoutubeFrag()
        getMovieDetail()
    }

    override fun onStart() {
        super.onStart()
        setToolbarTitle()
    }

    override fun observeData() {
        viewModel.movie.observe(this, Observer {
            it?.let {
                initPagerAdapter(it)
                if (it.videoResult.videos.isNotEmpty()){
                    createTrailer(it.videoResult.videos[0].key)
                }
            }
        })
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun setTextStatusBarColor() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        youtubeFragment.setFullScreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
    }

    override fun onBackPressed() {
        if (youtubeFragment.isYtbFullScreen) {
            youtubeFragment.setFullScreen(false)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_watch_movie -> {
            //todo
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun playTrailer(trailerKey: String) {
        log("playTrailer")
        youtubeFragment.setTrailerKey(trailerKey)
        youtubeFragment.playTrailer()
    }

    private fun initYoutubeFrag() {
        youtubeFragment =
            fragmentManager.findFragmentById(R.id.movieDetailYoutubeFragment) as YoutubeFragment
    }

    private fun initPagerAdapter(movie: Movie) {
        pagerAdapter = MovieDetailPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragments(InformationFragment.newInstance(movie))
        pagerAdapter.addFragments(TrailerFragment.newInstance(movie.videoResult))
        pagerAdapter.addFragments(CastFragment.newInstance(movie.credits.casts))
        pagerAdapter.addFragments(ProducerFragment.newInstance(movie.productionCompanies))
        movieDetailViewPager?.adapter = pagerAdapter
        movieDetailTabLayout?.setupWithViewPager(movieDetailViewPager, true)
    }

    private fun getMovieDetail() {
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        viewModel.getMovieDetail(movieId)
    }

    private fun createTrailer(trailerKey: String) {
        youtubeFragment.setTrailerKey(trailerKey)
    }

    private fun setToolbar() {
        setSupportActionBar(movieDetailToolBar)
    }

    private fun setToolbarTitle() {
        supportActionBar?.title = intent?.getStringExtra(EXTRA_MOVIE_TITLE)
    }

    private fun setBackDropView() {
        movieDetailToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        movieDetailToolBar.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"

        fun getIntent(context: Context, movieId: Int, title: String) =
            Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
                putExtra(EXTRA_MOVIE_TITLE, title)
            }
    }
}
