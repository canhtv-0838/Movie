package com.canh.movie.ui.landing.login

import com.canh.movie.R
import com.canh.movie.databinding.FragmentLoginBinding
import com.canh.movie.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(){

    override val viewModel: LoginViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_login

    override fun initComponents() {
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
