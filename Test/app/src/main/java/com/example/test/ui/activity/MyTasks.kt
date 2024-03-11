package com.example.test.ui.activity

import android.view.View
import com.example.test.databinding.ActivityMyTasksBinding
import com.example.test.ui.base.BaseActivity

class MyTasks : BaseActivity() {

    private lateinit var binding: ActivityMyTasksBinding
    override fun createView(): View {
        binding = ActivityMyTasksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonTest.setOnClickListener {
            moveToNextScreen(MainActivity::class.java)
        }

        buttonTask.setOnClickListener {
            moveToNextScreen(TravelActivity::class.java)
        }
    }

}