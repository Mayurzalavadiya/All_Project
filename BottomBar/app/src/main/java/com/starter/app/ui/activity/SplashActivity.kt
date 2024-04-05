package com.starter.app.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.starter.app.databinding.SplashActivityBinding
import com.starter.app.ui.auth.fragment.LoginFragment
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.fragmentTask.activity.FragmentTaskActivity
import com.starter.app.ui.productTask.activity.ProductListActivity

class SplashActivity : BaseActivity() {
    //Data store on after user login
    private lateinit var splashActivityBinding: SplashActivityBinding
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

//            loadActivity(AuthActivity::class.java).byFinishingCurrent().start()

//            loadActivity(ProductListActivity::class.java).byFinishingCurrent().start()
            loadActivity(TaskActivity::class.java).byFinishingCurrent().start()
//            loadActivity(HomeActivity::class.java).byFinishingCurrent().start()


        }, 2000)
    }

}