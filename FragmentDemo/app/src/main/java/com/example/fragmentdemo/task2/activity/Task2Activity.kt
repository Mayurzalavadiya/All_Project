package com.example.fragmentdemo.task2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentdemo.task2.fragment.StartFragment
import com.example.fragmentdemo.R

class Task2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, StartFragment())
            .commit()
    }
}