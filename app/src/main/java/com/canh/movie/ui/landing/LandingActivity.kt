package com.canh.movie.ui.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.canh.movie.R
import com.canh.movie.databinding.ActivityLandingBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.landing.splash.SplashFragment
import com.canh.movie.utils.log
import org.koin.android.viewmodel.ext.android.viewModel

class LandingActivity : BaseActivity<ActivityLandingBinding, LandingViewModel>() {
    override fun getContainerId(): Int = R.id.landingContainerId

    override val viewModel: LandingViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_landing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
    }

    override fun initComponents() {
        startFrag(SplashFragment.newInstance())
    }

    override fun observeData() {
        //To change body of created functions use File | Settings | File Templates.
    }

    private fun startFrag(fragment: Fragment) {
        replaceFragment(fragment, false)
    }

}
