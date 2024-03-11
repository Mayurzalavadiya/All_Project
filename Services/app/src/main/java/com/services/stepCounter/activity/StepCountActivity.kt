package com.services.stepCounter.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.services.R
import com.services.databinding.ActivityStepCountBinding
import com.services.stepCounter.service.StepCounterService

class StepCountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStepCountBinding
    private var isServiceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepCountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerBroadcast()
        setClickListener()

        // Check service status and update button icon
        isServiceRunning = StepCounterService.isServiceRunning
        updateButtonIcon()

    }


    private fun updateButtonIcon() {
        if (isServiceRunning) {
            binding.buttonStartStop.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_pause)
        } else {
            binding.buttonStartStop.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_play)
        }
    }

    override fun onResume() {
        super.onResume()

        // Check service status and update button icon
        if (StepCounterService.isServiceRunning) {
            updateButtonIcon()
            registerBroadcast()
        }
    }


    private fun registerBroadcast() {
        // Use the constant from StepCounterService for action
        LocalBroadcastManager.getInstance(this).registerReceiver(
            stepCountReceiver,
            IntentFilter(StepCounterService.STEP_COUNT_UPDATE_ACTION)
        )
    }

    private val stepCountReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val steps = intent?.getFloatExtra("steps", 0f) ?: 0f
            updateStepCount(steps)
        }
    }

    private fun updateStepCount(steps: Float) {
        binding.textViewSteps.text = getString(R.string.steps, steps.toInt().toString())
    }

    private fun setClickListener() = with(binding) {
        buttonStartStop.setOnClickListener {
            if (isServiceRunning) {
                stopStepCounterService()
                buttonStartStop.icon =
                    ContextCompat.getDrawable(this@StepCountActivity, R.drawable.ic_play)
            } else {
                startStepCounterService()
                buttonStartStop.icon =
                    ContextCompat.getDrawable(this@StepCountActivity, R.drawable.ic_pause)
            }
        }
    }
    private fun startStepCounterService() {
        isServiceRunning = true

        val intent = Intent(this@StepCountActivity, StepCounterService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ContextCompat.startForegroundService(this@StepCountActivity, intent)
        } else {
            startService(intent)
        }
    }

    private fun stopStepCounterService() {
        stopService(Intent(this@StepCountActivity, StepCounterService::class.java))
        isServiceRunning = false
    }
}