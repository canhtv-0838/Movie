package com.canh.movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import com.canh.movie.BR

abstract class BaseDialogFragment<VB : ViewDataBinding, VM : BaseViewModel> : DialogFragment(),
    LifecycleOwner {
    protected lateinit var viewDataBinding: VB
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, getLayoutResource(), container, false) as VB
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        setBindingVariables()
        initData()
        observeData()
    }

    @LayoutRes
    abstract fun getLayoutResource(): Int

    open fun setBindingVariables() {
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = this
    }

    protected open fun observeData() {
    }

    protected abstract fun initComponents()

    protected open fun initData() {
        viewModel.onCreate()
    }
}