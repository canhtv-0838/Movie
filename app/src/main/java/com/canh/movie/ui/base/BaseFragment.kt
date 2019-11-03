package com.canh.movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.canh.movie.BR
import com.canh.movie.utils.log

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment(), LifecycleOwner {
    protected lateinit var viewDataBinding: VB
    protected abstract val viewModel: VM
    private var containerId : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, getLayoutResource(), container, false) as VB
        containerId = container?.id
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

    open fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean
    ) =
        activity!!.supportFragmentManager.beginTransaction()
            .replace(containerId!!, fragment).apply {
                if (addToBackStack) addToBackStack(null)
            }.commit()

    open fun addFragment(
        fragment: Fragment,
        addToBackStack: Boolean
    ) =
        activity!!.supportFragmentManager.beginTransaction()
            .add(containerId!!, fragment).apply {
                if (addToBackStack) addToBackStack(null)
            }.commit()
}
