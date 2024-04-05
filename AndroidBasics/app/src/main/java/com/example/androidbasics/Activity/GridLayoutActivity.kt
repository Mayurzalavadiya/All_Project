package com.example.androidbasics.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.example.androidbasics.R

class GridLayoutActivity : AppCompatActivity() {

    var gridview: GridView? = null
    var users = arrayOf(
        "Mayur", "Nishita", "Bhavyesh", "Rahul", "Pratik", "Nishit", "Gautam",
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout)

        gridview = findViewById(R.id.gridview)

    }
}