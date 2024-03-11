package com.example.test.ui.activity

import android.annotation.SuppressLint
import android.view.View
import com.example.test.databinding.ActivitySplashBinding
import com.example.test.ui.auth.activity.AuthActivity
import com.example.test.ui.base.BaseActivity
import com.example.test.ui.base.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun createView(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
        val pref = getMyPref()
        CoroutineScope(Dispatchers.Main).launch {
            delay(0)
            if (pref.getBoolean(Const.IS_LOGIN)) {
                moveToNextScreen( MyTasks::class.java ,true)
                finish()
            } else {
                moveToNextScreen( AuthActivity::class.java,true)
                finish()
            }
        }
    }

}