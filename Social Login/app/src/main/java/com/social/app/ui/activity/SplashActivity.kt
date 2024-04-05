package com.social.app.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.social.app.databinding.SplashActivityBinding
import com.social.app.ui.auth.fragment.OnBoardingFragment
import com.social.app.ui.base.BaseActivity

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

            loadActivity(
                IsolatedActivity::class.java,
                OnBoardingFragment::class.java
            ).byFinishingCurrent().start()


            //loadActivity(HomeActivity::class.java).byFinishingCurrent().start()

        }, 2000)
    }
}