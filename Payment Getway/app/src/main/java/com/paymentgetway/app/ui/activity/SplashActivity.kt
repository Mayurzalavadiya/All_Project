package com.paymentgetway.app.ui.activity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.paymentgetway.app.R
import com.paymentgetway.app.databinding.SplashActivityBinding
import com.paymentgetway.app.ui.auth.fragment.LoginFragment
import com.paymentgetway.app.ui.base.BaseActivity

class SplashActivity : BaseActivity() {
    //Data store on after user login
    lateinit var splashActivityBinding: SplashActivityBinding

    private var animation: Animation? = null

    private lateinit var handler: Handler
    override fun findFragmentPlaceHolder(): Int {
        return 0
    }

    override fun createViewBinding(): View {
        splashActivityBinding = SplashActivityBinding.inflate(layoutInflater)
        return splashActivityBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Apply animation to the view
         animation = AnimationUtils.loadAnimation(this, R.anim.scroll_down)

        animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // Apply color change and show text view after animation ends
                startColorAnimation()
                splashActivityBinding.textViewSplash.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        // Apply animation to the view
        splashActivityBinding.view.startAnimation(animation)
        handler = Handler(Looper.getMainLooper())

        handler.postDelayed(Runnable {
            loadActivity(HomeActivity::class.java).byFinishingCurrent().start()
        },4500)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }


    override fun onDestroy() {
        // Stop animation and release resources when the activity is destroyed
        animation?.cancel()
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
    private fun startColorAnimation() {
        val startColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val endColor = ContextCompat.getColor(this, R.color.white_color)

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        colorAnimation.duration = 800 // Duration for the color change animation

        colorAnimation.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            splashActivityBinding.view.setBackgroundColor(color)
        }

        colorAnimation.start()
    }
}