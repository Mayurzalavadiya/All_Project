package com.example.sample26.activity

import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.sample26.databinding.ActivitySplashBinding
import com.example.sample26.ui.base.BaseActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding


    override fun createView(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (readBooleanPrefsVal(IS_LOGIN)) {

                moveToNextScreen(this, HomeActivity::class.java)
                finish()
            } else {
                moveToNextScreen(this, MainActivity::class.java)
                finish()
            }
        }, 2000)
    }
}
