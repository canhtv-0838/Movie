package com.canh.movie.ui.main.register

import android.app.DatePickerDialog
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.canh.movie.R
import com.canh.movie.databinding.DialogFragmentRegisterBinding
import com.canh.movie.ui.base.BaseDialogFragment
import com.canh.movie.ui.main.login.LoginDialogFragment
import com.canh.movie.utils.StringUtils
import com.canh.movie.utils.StringUtils.isEmail
import com.canh.movie.utils.StringUtils.isEnoughLength
import kotlinx.android.synthetic.main.dialog_fragment_register.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class RegisterDialogFragment :
    BaseDialogFragment<DialogFragmentRegisterBinding, RegisterViewModel>(), View.OnClickListener {

    private lateinit var datePicker: DatePickerDialog.OnDateSetListener

    private lateinit var calendar: Calendar

    override val viewModel: RegisterViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.dialog_fragment_register

    override fun initComponents() {
        calendar = Calendar.getInstance()
        pickDateOfBirth()
        setEvents()
    }

    override fun observeData() {
        super.observeData()
        viewModel.registerStatus.observe(this, Observer {
            it?.let {
                when (it) {
                    viewModel.REGISTER_FAILED -> {
                        registerProgressBar.visibility = View.GONE
                    }
                    viewModel.REGISTER_SUCCESS -> {
                        goToDialogLogin(true)
                    }
                }
            }
        })
        viewModel.resultMessage.observe(this, Observer {
            it?.let {
                if (it.isNotBlank()){
                    Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.registerButtonLogin -> {
                goToDialogLogin(false)
            }
            R.id.registerTextBirthday -> {
                showDatePicker()
            }
            R.id.registerButtonRegister -> {
                registerAccount()
            }
        }
    }

    private fun setEvents() {
        registerButtonRegister.setOnClickListener(this)
        registerButtonLogin.setOnClickListener(this)
        registerTextBirthday.setOnClickListener(this)
    }

    private fun goToDialogLogin(isRegister: Boolean) {
        if (isRegister) {
            LoginDialogFragment.newInstance(
                registerTextEmail.text.toString(),
                registerTextPassword.text.toString()
            )
                .show(activity!!.supportFragmentManager, LoginDialogFragment.TAG)
        } else {
            LoginDialogFragment.newInstance()
                .show(activity!!.supportFragmentManager, LoginDialogFragment.TAG)
        }
        dismiss()
    }

    private fun pickDateOfBirth() {
        datePicker = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTextBirthday()
        }
    }

    private fun showDatePicker() {
        DatePickerDialog(
            activity!!, datePicker,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun updateTextBirthday() {
        registerTextBirthday.setText(StringUtils.dateFormated(calendar.time))
    }

    private fun registerAccount() {
        if (!isEnoughLength(
                registerTextFullname,
                registerTextPlaceOfBirth,
                registerTextUsername,
                registerTextPassword,
                registerTextEmail
            , minlength = 4, maxLength = 50)
        ) {
            registerProgressBar.visibility = View.GONE
            return
        }
        if (!isEmail(registerTextEmail)) {
            registerProgressBar.visibility = View.GONE
            return
        }

        registerProgressBar.visibility = View.VISIBLE

        var gender = 0 //gender = 0: male, 1: female
        if (registerRadioMale.isChecked) {
            gender = 0
        }
        if (registerRadioFemale.isChecked){
            gender = 1
        }

        viewModel.registerAccount(
            registerTextFullname.text.toString(),
            registerTextBirthday.text.toString(),
            gender,
            registerTextPlaceOfBirth.text.toString(),
            registerTextUsername.text.toString(),
            registerTextPassword.text.toString(),
            registerTextEmail.text.toString(),
            System.currentTimeMillis()
        )
    }

    companion object {
        const val TAG = "RegisterDialogFragment"

        fun newInstance() = RegisterDialogFragment()
    }

}
