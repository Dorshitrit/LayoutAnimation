package com.dor.layoutanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver

fun setLayoutAnimate(
    rootView: View?,
    action: (viewOldY: Float?, viewNewY: Float?, viewOldMeasuredHeight: Int?, viewNewMeasuredHeight: Int) -> Unit
) {
    val viewOldY = rootView?.y
    val viewOldMeasuredHeight = rootView?.measuredHeight
    rootView?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (rootView == null) {
                Log.e("setLayoutAnimate", "rootView null")
                return
            }
            rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)

            action(viewOldY, rootView.y, viewOldMeasuredHeight, rootView.measuredHeight)
        }
    })
}

fun setOpenLayoutAnimation(view: View?, startValue: Float, endValue: Float, duration: Long = 500, action: () -> Unit) {
    val anim =
        ValueAnimator.ofFloat(startValue, endValue)
            .setDuration(duration)
    anim.addUpdateListener { animation ->
        view?.y = animation.animatedValue as Float
        view?.requestLayout()
    }

    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            action()
        }
    })


    anim.start()
}