package com.canh.movie.ui.main.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.canh.movie.R
import com.canh.movie.data.model.User
import com.canh.movie.databinding.DialogFragmentLoginBinding
import com.canh.movie.ui.base.BaseDialogFragment
import com.canh.movie.ui.main.register.RegisterDialogFragment
import com.canh.movie.utils.Constants
import com.canh.movie.utils.SharedPreference
import com.canh.movie.utils.StringUtils.isEmail
import com.canh.movie.utils.StringUtils.isEnoughLength
import kotlinx.android.synthetic.main.dialog_fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginDialogFragment : BaseDialogFragment<DialogFragmentLoginBinding, LoginViewModel>() {

    private lateinit var loginListener: LoginListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginListener = context as LoginListener
    }

    override val viewModel: LoginViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.dialog_fragment_login

    override fun initComponents() {
        setEvents()
    }

    override fun initData() {
        super.initData()
        arguments?.getString(ARGUMENT_EMAIL)?.let {
            loginTextEmail.setText(it)
        }
        arguments?.getString(ARGUMENT_PASSWORD)?.let {
            loginTextPassword.setText(it)
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.loginStatus.observe(this, Observer {
            it?.let {
                when (it) {
                    viewModel.LOGIN_FAILED -> {
                        loginProgressBar?.visibility = View.GONE
                        Toast.makeText(activity!!, "Login failed. Try it again", Toast.LENGTH_LONG)
                            .show()
                    }
                    viewModel.LOGIN_SUCCESS -> {
                        SharedPreference(activity!!).saveValueBoolean(
                            Constants.PREF_LOGGED_IN, true
                        )
                        loginProgressBar?.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.idUser.observe(this, Observer {
            it?.let {
                SharedPreference(activity!!).saveValueLong(
                    Constants.PREF_ID_USER, it
                )
            }
        })

        viewModel.user.observe(this, Observer {
            loginListener.onLoginSuccess(it)
            dismiss()
        })
    }

    private fun setEvents() {
        loginButtonRegister.setOnClickListener {
            RegisterDialogFragment.newInstance()
                .show(activity!!.supportFragmentManager, RegisterDialogFragment.TAG)
            dismiss()
        }
        loginButtonLogin.setOnClickListener {
            loginAccount()
        }
    }

    private fun loginAccount() {
        if (!isEnoughLength(loginTextEmail, loginTextPassword, minlength = 4, maxLength = 50)) {
            return
        }

        if (!isEmail(loginTextEmail)) {
            return
        }
        loginProgressBar?.visibility = View.VISIBLE
        viewModel.loginAccount(loginTextEmail.text.toString(), loginTextPassword.text.toString())
    }

    companion object {
        const val TAG = "LoginDialogFragment"

        private const val ARGUMENT_EMAIL = "ARGUMENT_EMAIL"
        private const val ARGUMENT_PASSWORD = "ARGUMENT_PASSWORD"

        fun newInstance() = LoginDialogFragment()

        fun newInstance(email: String, password: String) = LoginDialogFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_EMAIL, email)
                putString(ARGUMENT_PASSWORD, password)
            }
        }
    }

    interface LoginListener {
        fun onLoginSuccess(user : User)
    }
}
