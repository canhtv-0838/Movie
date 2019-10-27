package com.canh.movie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.canh.movie.BR

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), LifecycleOwner {

    protected lateinit var viewDataBinding: VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
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

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResource())
        viewDataBinding.lifecycleOwner = this
    }

}
