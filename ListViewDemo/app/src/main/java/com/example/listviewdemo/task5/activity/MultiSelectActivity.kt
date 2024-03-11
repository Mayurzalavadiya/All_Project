package com.example.listviewdemo.task5.activity

import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.ActivityMultiSelectBinding
import com.example.listviewdemo.task5.adapter.MultiSelectAdapter
import com.example.listviewdemo.task5.fragment.ItemsFragment
import com.example.listviewdemo.task5.fragment.SelectItemsFragment
import com.example.listviewdemo.task5.listeners.ClickListener
import com.example.listviewdemo.task5.pojo.MultiSelectData

class MultiSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMultiSelectBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ItemsFragment())
            .commit()
    }


}