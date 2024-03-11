package com.rapido.app.ui.activity


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.rapido.app.di.DiConstants
import com.rapido.app.ui.base.BaseActivity
import com.rapido.app.databinding.SplashActivityBinding

class SplashActivity : BaseActivity() {


    //Data store on after user login
    lateinit var binding: SplashActivityBinding

    override fun findFragmentPlaceHolder(): Int {
        return 0
    }

    override fun createViewBinding(): View {
        binding = SplashActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            if (appPreferences.getBoolean(DiConstants.IS_LOGIN)) {
                loadActivity(HomeActivity::class.java).byFinishingCurrent().start()
            } else {
                loadActivity(AuthActivity::class.java).byFinishingCurrent().start()
            }

        }, 2000)
    }

}