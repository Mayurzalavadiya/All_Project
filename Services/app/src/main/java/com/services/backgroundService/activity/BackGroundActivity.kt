package com.services.backgroundService.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.services.databinding.ActivityBackGroundBinding
import com.services.backgroundService.service.BackgroundService

class BackGroundActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBackGroundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackGroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()


    }

    private fun setClickListener() = with(binding) {
        buttonStartServices.setOnClickListener {
            val intent = Intent(this@BackGroundActivity, BackgroundService::class.java)
            startService(intent)
        }

        buttonStopServices.setOnClickListener {
            val intent = Intent(this@BackGroundActivity, BackgroundService::class.java)
            stopService(intent)
        }

    }
}