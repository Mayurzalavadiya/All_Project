package com.constraint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CircleActivity : AppCompatActivity() {

    val fruits = listOf("Apple", "Banana", "Orange", "Apple")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)

        fruits.forEach { fruit ->
            println("Fruit: $fruit")
        }

        for (i in 0 until 10 ) {
            println(i)
        }



    }
}