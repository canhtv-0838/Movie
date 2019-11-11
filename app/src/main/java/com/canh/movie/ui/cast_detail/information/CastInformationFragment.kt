package com.canh.movie.ui.cast_detail.information

import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.People
import com.canh.movie.databinding.FragmentCastInformationBinding
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.utils.log
import org.koin.android.viewmodel.ext.android.viewModel

class CastInformationFragment :
    BaseFragment<FragmentCastInformationBinding, CastInformationViewModel>() {

    override val viewModel: CastInformationViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_cast_information

    override fun initComponents() {
    }

    override fun initData() {
        super.initData()
        arguments?.getParcelable<People>(ARGUMENT_PEOPLE)?.let {
            viewModel.postPerson(it)
        }
    }

    companion object {
        private const val ARGUMENT_PEOPLE = "ARGUMENT_PEOPLE"

        fun newInstance(people: People) = CastInformationFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_PEOPLE, people)
            }
        }
    }
}
