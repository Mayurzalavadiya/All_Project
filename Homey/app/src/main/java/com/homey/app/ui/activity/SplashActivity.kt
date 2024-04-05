package com.homey.app.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.homey.app.R
import com.homey.app.core.AppPreferences
import com.homey.app.databinding.SplashActivityBinding
import com.homey.app.ui.auth.fragment.LoginFragment
import com.homey.app.ui.auth.fragment.OnBoardingFragment
import com.homey.app.ui.base.BaseActivity
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    //Data store on after user login


    lateinit var splashActivityBinding: SplashActivityBinding

    override fun findFragmentPlaceHolder(): Int {
        return 0
    }

    override fun createViewBinding(): View {
        splashActivityBinding = SplashActivityBinding.inflate(layoutInflater)
        return splashActivityBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({

            if (appPreferences.getBoolean(Keys.IS_LOGIN)) {
                loadActivity(HomeActivity::class.java).byFinishingCurrent().start()
            } else {
                loadActivity(
                    IsolatedActivity::class.java,
                    OnBoardingFragment::class.java
                ).byFinishingCurrent().start()
            }
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        }, 2000)
    }

}