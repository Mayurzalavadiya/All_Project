package com.asis.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.asis.R
import com.asis.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
        override fun onStart()  = with(binding){
            super.onStart()
            editTextEmail.setText(intent?.getStringExtra(CommonClass.MOBILE_NUMBER))
            editTextPassword.setText(intent?.getStringExtra(CommonClass.PASS))
    }
}