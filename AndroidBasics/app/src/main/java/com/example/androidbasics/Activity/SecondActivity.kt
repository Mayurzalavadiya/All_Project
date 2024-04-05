package com.example.androidbasics.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidbasics.R

class SecondActivity : AppCompatActivity() {

    var textName: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        textName = findViewById(R.id.textName)

        val bundle = intent.extras
        textName?.text = bundle!!.getString("name")

    }
}