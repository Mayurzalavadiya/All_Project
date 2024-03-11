package com.example.sample26.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample26.databinding.ActivityRestaurantDetailsBinding

class RestaurantDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}