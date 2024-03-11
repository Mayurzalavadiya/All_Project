package com.starter.app.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.starter.app.core.AppPreferences
import com.starter.app.databinding.SplashActivityBinding
import com.starter.app.di.DiConstants
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.home.activity.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    //Data store on after user login
    private lateinit var splashActivityBinding: SplashActivityBinding

    @Inject
    lateinit var appPreferences: AppPreferences

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

//            loadActivity(
//                IsolatedActivity::class.java,
//                OnBoardingFragment1::class.java
//            ).byFinishingCurrent().start()

            if (appPreferences.getBoolean(DiConstants.IS_LOGIN)) {
                loadActivity(ProfileActivity::class.java).byFinishingCurrent().start()
            } else {
                loadActivity(AuthActivity::class.java).byFinishingCurrent().start()
            }

        }, 2000)
    }

}