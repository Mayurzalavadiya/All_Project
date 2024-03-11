package com.example.fragmentdemo.mainActivity

import android.content.Intent
import android.os.Bundle
import com.example.fragmentdemo.databinding.ActivityMainBinding
import com.example.fragmentdemo.task1.activity.FragmentDemoActivity
import com.example.fragmentdemo.task2.activity.Task2Activity
import com.example.fragmentdemo.task3.activity.TaskActivity
import com.example.fragmentdemo.task4.activity.FragmentPagerActivity
import com.example.fragmentdemo.task4.activity.FragmentStatePagerActivity
import com.example.fragmentdemo.task4.activity.ViewPagerActivity
import com.example.fragmentdemo.task5.activity.ViewPager2Activity
import com.example.fragmentdemo.task6.activity.ViewPager2TaskActivity
import com.example.fragmentdemo.task7.activity.ViewpagerWithRecyclerActivity
import com.example.fragmentdemo.task8.activity.ViewPagerWithFragmentActivity
import com.example.fragmentdemo.task9.activity.ViewPagerScrollBothSideActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonTask1.setOnClickListener {
            moveToNextScreen(this@MainActivity, FragmentDemoActivity::class.java)
        }

        buttonTask2.setOnClickListener {
            moveToNextScreen(this@MainActivity, Task2Activity::class.java)
        }
        buttonTask3.setOnClickListener {
            moveToNextScreen(this@MainActivity, TaskActivity::class.java)
        }
        buttonViewPager.setOnClickListener {
            moveToNextScreen(this@MainActivity, ViewPagerActivity::class.java)
        }
        buttonFragmentPager.setOnClickListener {
            moveToNextScreen(this@MainActivity, FragmentPagerActivity::class.java)
        }
        buttonFragmentStatePager.setOnClickListener {
            moveToNextScreen(this@MainActivity, FragmentStatePagerActivity::class.java)
        }
        buttonViewPager2.setOnClickListener {
            moveToNextScreen(this@MainActivity, ViewPager2Activity::class.java)
        }
        buttonViewPager2Task.setOnClickListener {
            moveToNextScreen(this@MainActivity, ViewPager2TaskActivity::class.java)
        }
        buttonViewPagerRecyclerTask.setOnClickListener {
            moveToNextScreen(this@MainActivity, ViewpagerWithRecyclerActivity::class.java)
        }
        buttonViewPagerFragmentTask.setOnClickListener {
            moveToNextScreen(this@MainActivity, ViewPagerWithFragmentActivity::class.java)
        }
        buttonViewPagerBothScroll.setOnClickListener {
            moveToNextScreen(this@MainActivity, ViewPagerScrollBothSideActivity::class.java)
        }
    }
}