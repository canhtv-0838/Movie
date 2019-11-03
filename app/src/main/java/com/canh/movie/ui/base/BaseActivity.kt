package com.canh.movie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.canh.movie.BR
import com.canh.movie.utils.Constants
import com.canh.movie.utils.SharedPreference

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    LifecycleOwner {

    protected lateinit var viewDataBinding: VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        setNightMode()
        super.onCreate(savedInstanceState)
        performDataBinding()
        setBindingVariables()
        initComponents()
        observeData()
    }

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected open fun setBindingVariables() {
        viewModel.onCreate()
        viewDataBinding.setVariable(BR.viewModel, viewModel)
    }

    protected abstract fun initComponents()

    protected abstract fun observeData()

    protected abstract fun getContainerId(): Int

    protected open fun setNightMode() {
        val isNight = SharedPreference(this).getValueBoolean(Constants.NIGH_MODE, false)
        AppCompatDelegate.setDefaultNightMode(
            if (isNight) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        delegate.applyDayNight()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResource())
        viewDataBinding.lifecycleOwner = this
    }

    open fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean
    ) =
        supportFragmentManager.beginTransaction()
            .replace(getContainerId(), fragment).apply {
                if (addToBackStack) addToBackStack(null)
            }.commit()

    open fun addFragment(
        fragment: Fragment,
        addToBackStack: Boolean
    ) =
        supportFragmentManager.beginTransaction()
            .add(getContainerId(), fragment).apply {
                if (addToBackStack) addToBackStack(null)
            }.commit()
}
