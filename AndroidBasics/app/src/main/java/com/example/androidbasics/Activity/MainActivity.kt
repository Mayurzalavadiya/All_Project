package com.example.androidbasics.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.androidbasics.R

class MainActivity : AppCompatActivity() {

    var animation: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animation = findViewById(R.id.animation)

            intent = Intent(this, Activity1::class.java)
            startActivity(intent)
    }
}