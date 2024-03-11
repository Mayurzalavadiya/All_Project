package com.example.fragmentdemo.task3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentdemo.task3.adapter.Task1Fragment
import com.example.fragmentdemo.task3.adapter.Task2Fragment
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, Task2Fragment())
            .commit()


        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer1, Task1Fragment())
            .commit()
    }
}