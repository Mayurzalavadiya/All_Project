package com.example.coroutines.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.coroutines.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i("Second Activity", "Second Activity Running ....")
        Toast.makeText(this, "Second Activity", Toast.LENGTH_SHORT).show()
    }
}