package com.example.fragmentdemo.task4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityViewPagerBinding
import com.example.fragmentdemo.task4.adapter.CustomPagerAdapter

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setViewPager()
    }

    private fun setViewPager() = with(binding) {
        viewPager.adapter = CustomPagerAdapter(getItem())

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun getItem(): MutableList<Int> {
        return mutableListOf<Int>().apply {
            add(R.drawable.image1)
            add(R.drawable.image2)
            add(R.drawable.image3)
            add(R.drawable.image4)
            add(R.drawable.image5)
            add(R.drawable.image6)
            add(R.drawable.image7)
            add(R.drawable.image8)
            add(R.drawable.image9)
            add(R.drawable.image10)
        }
    }
}