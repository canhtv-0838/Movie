package com.canh.movie.ui.movie_detail.producer

import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.Company
import com.canh.movie.databinding.FragmentProducerBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.producer_detail.ProducerDetailActivity
import com.canh.movie.utils.log
import org.koin.android.viewmodel.ext.android.viewModel

class ProducerFragment : BaseFragment<FragmentProducerBinding, ProducerViewModel>(),
    BaseAdapterItemClickListener<Company> {

    override val viewModel: ProducerViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_producer

    override fun initComponents() {
        listener = this
    }

    override fun initData() {
        super.initData()
        arguments?.getParcelableArrayList<Company>(ARGUMENT_COMPANIES)?.let{
            viewModel.postCompanies(it)
        }
    }

    override fun onItemClick(item: Company) {
        startActivity(ProducerDetailActivity.getIntent(activity!!,item.id, item.name))
    }

    companion object {
        private const val ARGUMENT_COMPANIES = "ARGUMENT_COMPANIES"

        @JvmStatic
        lateinit var listener: BaseAdapterItemClickListener<Company>

        fun newInstance(companies: List<Company>) = ProducerFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARGUMENT_COMPANIES, companies as ArrayList)
            }
        }
    }
}
