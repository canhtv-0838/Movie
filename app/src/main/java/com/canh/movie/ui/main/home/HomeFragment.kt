package com.canh.movie.ui.main.home

import android.os.Handler
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.data.model.pair.CategoryPair
import com.canh.movie.databinding.FragmentHomeBinding
import com.canh.movie.ui.base.BaseAdapter
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.main.home.category.CategoryListener
import com.canh.movie.ui.main.home.slide.SlideAdapter
import com.canh.movie.utils.log
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

private const val DELAY_TIME_SLIDE = 3000L
private const val PERIOD_TIME_SLIDE = 3000L

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    SlideAdapter.SlideListener,
    ViewPager.OnPageChangeListener,
    BaseAdapterItemClickListener<Movie>,
    CategoryListener<CategoryPair> {

    private lateinit var slideAdapter: SlideAdapter
    private var motionSlidePosition = 0
    private var currentSlidePosition = 0
    private var slideSize = 0

    override val viewModel: HomeViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun initComponents() {
        initSlideAdapter()
        moviesListener = this
        categoryListener = this
    }

    override fun observeData() {
        super.observeData()
        viewModel.trendingResponse.observe(this, Observer {
            it?.let {
                slideAdapter.setItem(it.results)
                slideSize = it.results.size
            }
        })
        viewModel.messageNotification.observe(this, Observer {
            it?.let {
                log("Mess: $it")
            }
        })
    }

    override fun onSlideClick(movies: List<Movie>) {
        log("${movies[currentSlidePosition].title}")
        //todo
    }

    override fun onItemClick(item: Movie) {
        log("${item.title}")
    }

    override fun onCategoryClick(item: CategoryPair) {
        log("meow")
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        motionSlidePosition = position
        currentSlidePosition = position
    }

    private fun initSlideAdapter() {
        slideAdapter = SlideAdapter()
        slideAdapter.setListener(this)

        homeViewPager.adapter = slideAdapter
        homeViewPager.addOnPageChangeListener(this)
        homeTabLayout.setupWithViewPager(homeViewPager, true)
        initTimerChangeSlide()
    }

    private fun initTimerChangeSlide() {
        val handler = Handler()
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    if (motionSlidePosition == slideSize) {
                        motionSlidePosition = 0
                    }
                    homeViewPager?.setCurrentItem(motionSlidePosition++, true)
                }
            }
        }, DELAY_TIME_SLIDE, PERIOD_TIME_SLIDE)
    }

    companion object : BaseAdapter.OnItemClickListener {
        fun newInstance() = HomeFragment()

        @JvmStatic
        lateinit var moviesListener: BaseAdapterItemClickListener<Movie>

        @JvmStatic
        lateinit var categoryListener: CategoryListener<CategoryPair>
    }

}
