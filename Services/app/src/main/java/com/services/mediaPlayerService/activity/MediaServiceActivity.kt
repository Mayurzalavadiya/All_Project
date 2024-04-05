package com.services.mediaPlayerService.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.services.databinding.ActivityMediaServiceBinding
import com.services.mediaPlayerService.service.NewService

class MediaServiceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMediaServiceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        buttonStartServices.setOnClickListener(this@MediaServiceActivity)
        buttonStopService.setOnClickListener(this@MediaServiceActivity)
    }

    override fun onClick(v: View?) {
        if (v === binding.buttonStartServices) {

            // starting the service
            startService(Intent(this@MediaServiceActivity, NewService::class.java))
        }

        // process to be performed
        // if stop button is clicked
        else if (v === binding.buttonStopService) {

            // stopping the service
            stopService(Intent(this@MediaServiceActivity, NewService::class.java))

        }
    }

}