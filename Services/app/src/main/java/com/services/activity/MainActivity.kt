package com.services.activity

import android.os.Bundle
import com.services.backgroundService.activity.BackGroundActivity
import com.services.boundService.activity.ActivityA
import com.services.databinding.ActivityMainBinding
import com.services.foregroundService.activity.ForeGroundActivity
import com.services.pushNotification.activity.NotificationActivity
import com.services.stepCounter.activity.StepCountActivity
import com.services.stopWatch.activity.StopWatchActivity
import com.services.workManager.activity.WorkManagerActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonBackgroundService.setOnClickListener {
            moveToNextScreen(this@MainActivity, BackGroundActivity::class.java)
        }

        buttonForeGroundService.setOnClickListener {
            moveToNextScreen(this@MainActivity, ForeGroundActivity::class.java)
        }
        buttonBoundService.setOnClickListener {
            moveToNextScreen(this@MainActivity, ActivityA::class.java)
        }
        buttonNotification.setOnClickListener {
            moveToNextScreen(this@MainActivity, NotificationActivity::class.java)
        }
        buttonStopWatch.setOnClickListener {
            moveToNextScreen(this@MainActivity, StopWatchActivity::class.java)
        }
        buttonStepCounter.setOnClickListener {
            moveToNextScreen(this@MainActivity, StepCountActivity::class.java)
        }
        buttonWorkManager.setOnClickListener {
            moveToNextScreen(this@MainActivity, WorkManagerActivity::class.java)
        }
    }
}