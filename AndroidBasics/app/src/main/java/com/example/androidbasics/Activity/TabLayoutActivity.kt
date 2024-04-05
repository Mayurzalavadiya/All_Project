package com.example.androidbasics.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.androidbasics.R

class TabLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        val toolbar:Toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }
}