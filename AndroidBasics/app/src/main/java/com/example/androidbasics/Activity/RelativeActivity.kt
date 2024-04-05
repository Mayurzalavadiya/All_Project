package com.example.androidbasics.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.androidbasics.R

class RelativeActivity : AppCompatActivity() {

    var gender: RadioGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)

        gender = findViewById(R.id.gender)

        gender?.setOnCheckedChangeListener { group, checkedId ->
            var radio :RadioButton = findViewById(checkedId)
            Toast.makeText(this,radio.text,Toast.LENGTH_SHORT).show()
        }
    }
}