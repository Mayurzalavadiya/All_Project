package com.example.androidbasics.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.androidbasics.R
import com.example.androidbasics.R.*

class FrameLayoutActivity : AppCompatActivity() {

    private var switch: AppCompatButton? = null
    private var text1: AppCompatTextView? = null
    private var text2: AppCompatTextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_frame_layout)

//        switch = findViewById(R.id.button_switch)
//        text1 = findViewById(R.id.text_1)
//        text2 = findViewById(R.id.text_2)
        switch?.setOnClickListener() {
            if (text1?.visibility == View.GONE) {
                text1?.visibility = View.VISIBLE
                text2?.visibility = View.GONE
            } else {
                text1?.visibility = View.GONE
                text2?.visibility = View.VISIBLE
            }
        }
    }
}