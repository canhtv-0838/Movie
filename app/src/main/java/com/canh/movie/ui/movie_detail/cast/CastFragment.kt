package com.canh.movie.ui.movie_detail.cast

import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.Cast
import com.canh.movie.databinding.FragmentCastBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.cast_detail.CastDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class CastFragment : BaseFragment<FragmentCastBinding, CastViewModel>(),
    BaseAdapterItemClickListener<Cast> {

    override val viewModel: CastViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_cast

    override fun initComponents() {
        listener = this
    }

    override fun initData() {
        super.initData()
        arguments?.getParcelableArrayList<Cast>(ARGUMENT_CASTS)?.let {
            viewModel.postCasts(it)
        }
    }

    override fun onItemClick(item: Cast) {
        startActivity(CastDetailActivity.getIntent(activity!!, item.id))
    }

    companion object {
        private const val ARGUMENT_CASTS = "ARGUMENT_CASTS"

        @JvmStatic
        lateinit var listener: BaseAdapterItemClickListener<Cast>

        fun newInstance(casts: List<Cast>) = CastFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARGUMENT_CASTS, casts as ArrayList)
            }
        }
    }
}
