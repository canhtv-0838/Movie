package com.canh.movie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewDataBinding: VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setBindingVariables()
        observeData()
        initComponents()
    }

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected open fun setBindingVariables() {
        viewModel.create()
    }

    protected abstract fun initComponents()

    protected abstract fun observeData()

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResource())
    }

}
