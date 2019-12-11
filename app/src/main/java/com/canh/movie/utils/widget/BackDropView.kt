package com.canh.movie.utils.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import android.widget.ImageView
import com.canh.movie.utils.Constants
import com.canh.movie.utils.SharedPreference

class BackDropView @JvmOverloads internal constructor(
    private val context: Context, private val sheet: View, private val interpolator: Interpolator? = null,
    private val openIcon: Drawable? = null, private val closeIcon: Drawable? = null) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private val height: Int
    private var backdropShown = false

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
    }

    override fun onClick(view: View) {
        backdropShown = !backdropShown
        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()
        updateIcon(view)
        val translateY = if (SharedPreference(context).getValueBoolean(Constants.PREF_LOGGED_IN,false)
        ) {
            (height - (3*height)/4)
        } else {
            (height - (15*height)/16)
        }
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        animatorSet.play(animator)
        animator.start()
    }

    private fun updateIcon(view: View) {
        if (openIcon != null && closeIcon != null) {
            if (view !is ImageView) {
                throw IllegalArgumentException()
            }
            if (backdropShown) {
                view.setImageDrawable(closeIcon)
            } else {
                view.setImageDrawable(openIcon)
            }
        }
    }

    fun onLoggedIn(){
        backdropShown = !backdropShown
        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()
        val translateY = height - (3*height)/4
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        animatorSet.play(animator)
        animator.start()
    }

    fun onLoggedOut(){
        backdropShown = !backdropShown
        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()
        val translateY = height - (15*height)/16
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        animatorSet.play(animator)
        animator.start()
    }

    fun close(){
        backdropShown = !backdropShown
        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()
        val translateY = 0
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        animatorSet.play(animator)
        animator.start()
    }

}
