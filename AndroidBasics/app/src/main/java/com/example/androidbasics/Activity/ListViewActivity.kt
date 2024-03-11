package com.example.androidbasics.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.androidbasics.R

class ListViewActivity : AppCompatActivity() {
    var listview: ListView? = null
    var users = arrayOf(
        "Mayur", "Nishita", "Bhavyesh", "Rahul", "Pratik", "Nishit", "Gautam",
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        listview = findViewById(R.id.user_list)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        listview?.adapter = arrayAdapter
        listview?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, users[position], Toast.LENGTH_SHORT).show()
        }

    }
}