package com.example.androidbasics.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import com.example.androidbasics.R

class Activity4 : AppCompatActivity() {

    private var imageView: AppCompatImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        imageView = findViewById(R.id.imageView)

        imageView?.setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
        }
    }
}