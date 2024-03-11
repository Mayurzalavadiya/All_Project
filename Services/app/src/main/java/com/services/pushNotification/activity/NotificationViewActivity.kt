package com.services.pushNotification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.services.R
import com.services.databinding.ActivityNotificationViewBinding

class NotificationViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = intent.getStringExtra("message");
    }
}