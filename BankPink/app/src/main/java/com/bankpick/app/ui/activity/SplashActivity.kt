package com.bankpick.app.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bankpick.app.databinding.SplashActivityBinding
import com.bankpick.app.ui.auth.fragment.LoginFragment
import com.bankpick.app.ui.base.BaseActivity

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

//            loadActivity(
//                IsolatedActivity::class.java,
//                LoginFragment::class.java
//            ).byFinishingCurrent().start()

            loadActivity(AuthActivity::class.java).byFinishingCurrent().start()

        }, 2000)
    }

}