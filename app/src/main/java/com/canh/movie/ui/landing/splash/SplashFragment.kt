package com.canh.movie.ui.landing.splash

import android.graphics.drawable.Drawable
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.canh.movie.R
import com.canh.movie.databinding.FragmentSplashBinding
import com.canh.movie.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val TIME_DELAY = 3000L
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun getLayoutResource(): Int = R.layout.fragment_splash

    override val viewModel: SplashViewModel by viewModel()

    override fun initComponents() {
        motion_layout.transitionToEnd()

        val topLeftAnimationForward =
            AnimatedVectorDrawableCompat.create(context!!, R.drawable.top_left_liquid_forward)
        val topLeftAnimationReverse =
            AnimatedVectorDrawableCompat.create(context!!, R.drawable.top_left_liquid_reverse)
        val bottomRightAnimationForward =
            AnimatedVectorDrawableCompat.create(context!!, R.drawable.bottom_right_liquid_forward)
        val bottomRightAnimationReverse =
            AnimatedVectorDrawableCompat.create(context!!, R.drawable.bottom_right_liquid_reverse)
        val topLeftImageView = imageTopLeft.apply {
            setImageDrawable(topLeftAnimationForward)
        }
        val bottomRightImageView = imageBottomRight.apply {
            setImageDrawable(bottomRightAnimationForward)
        }
        topLeftAnimationForward?.registerAnimationCallback(object :
            Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                topLeftImageView.setImageDrawable(topLeftAnimationReverse)
                topLeftAnimationReverse?.start()

            }
        })
        topLeftAnimationReverse?.registerAnimationCallback(object :
            Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                topLeftImageView.setImageDrawable(topLeftAnimationForward)
                topLeftAnimationForward?.start()
            }
        })
        bottomRightAnimationForward?.registerAnimationCallback(object :
            Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                bottomRightImageView.setImageDrawable(bottomRightAnimationReverse)
                bottomRightAnimationReverse?.start()
            }
        })
        bottomRightAnimationReverse?.registerAnimationCallback(object :
            Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                bottomRightImageView.setImageDrawable(bottomRightAnimationForward)
                bottomRightAnimationForward?.start()
            }
        })
        topLeftAnimationForward?.start()
        bottomRightAnimationForward?.start()
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}
