package com.canh.movie.ui.producer_detail

import android.content.Context
import android.content.Intent
import com.canh.movie.R
import com.canh.movie.data.model.Movie
import com.canh.movie.databinding.ActivityProducerDetailBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import com.canh.movie.utils.log
import kotlinx.android.synthetic.main.activity_producer_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProducerDetailActivity :
    BaseActivity<ActivityProducerDetailBinding, ProducerDetailViewModel>(),
    BaseAdapterItemClickListener<Movie> {

    override val viewModel: ProducerDetailViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_producer_detail

    override fun initComponents() {
        listener = this

        setToolbarTitle()
        setToolbar()
        setBackDropView()

        getMovies()
    }

    override fun observeData() {
    }

    override fun getContainerId(): Int = 0

    override fun onItemClick(item: Movie) {
        startActivity(MovieDetailActivity.getIntent(this,item.id, item.title))
    }

    //todo - có quá ít dữ liệu về company nên không cần hiển thị
    private fun getProducerDetail() {
        intent?.getIntExtra(EXTRA_COMPANY_ID, 0)?.let { companyId ->
            viewModel.getCompanyDetail(companyId)
        }
    }

    private fun getMovies() {
        intent?.getIntExtra(EXTRA_COMPANY_ID, 0)?.let { companyId ->
            viewModel.getMovies(companyId)
        }
    }

    private fun setToolbar() {
        setSupportActionBar(producerDetailToolBar)
    }

    private fun setToolbarTitle() {
        intent?.getStringExtra(EXTRA_COMPANY_NAME)?.let{
            supportActionBar?.title = it
        }
    }

    private fun setBackDropView() {
        producerDetailToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        producerDetailToolBar.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        private const val EXTRA_COMPANY_ID = "EXTRA_COMPANY_ID"
        private const val EXTRA_COMPANY_NAME = "EXTRA_COMPANY_NAME"

        lateinit var listener: BaseAdapterItemClickListener<Movie>

        fun getIntent(context: Context, companyId: Int, companyName: String) =
            Intent(context, ProducerDetailActivity::class.java).apply {
                putExtra(EXTRA_COMPANY_ID, companyId)
                putExtra(EXTRA_COMPANY_NAME, companyName)
            }
    }

}