package com.services.foregroundService.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.services.databinding.ActivityForeGroundBinding
import com.services.foregroundService.service.ForegroundService

class ForeGroundActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForeGroundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForeGroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonStartServices.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(
                    Intent(
                        this@ForeGroundActivity,
                        ForegroundService::class.java
                    )
                )
            }
        }

        buttonStopServices.setOnClickListener {
            stopService(Intent(this@ForeGroundActivity, ForegroundService::class.java))
        }
    }
}