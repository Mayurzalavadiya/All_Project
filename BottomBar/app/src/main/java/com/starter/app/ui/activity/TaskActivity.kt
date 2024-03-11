package com.starter.app.ui.activity

import android.os.Bundle
import android.view.View
import com.starter.app.databinding.ActivityTask2Binding
import com.starter.app.ui.apicalldemo.activity.ApiDemoActivity
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.bitcoin.activity.BitCoinActivity
import com.starter.app.ui.bottomBar.BottomBarActivity
import com.starter.app.ui.fragmentTask.activity.FragmentTaskActivity
import com.starter.app.ui.location.LocationActivity
import com.starter.app.ui.navbar.activity.NavigationActivity
import com.starter.app.ui.productTask.activity.ProductListActivity
import com.starter.app.ui.testTask.activity.TaskActivity

class TaskActivity : BaseActivity() {

    private lateinit var binding: ActivityTask2Binding

    override fun findFragmentPlaceHolder(): Int = 0

    override fun createViewBinding(): View {
        binding = ActivityTask2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setClickListener()
    }

    private fun setClickListener() = with(binding) {

        buttonApiCallDemo.setOnClickListener {
            loadActivity(ApiDemoActivity::class.java).start()
        }
        buttonFragmentDemo.setOnClickListener {
            loadActivity(FragmentTaskActivity::class.java).start()
        }
        buttonTestDemo.setOnClickListener {
            loadActivity(TaskActivity::class.java).start()
        }

        buttonProductTask.setOnClickListener {
            loadActivity(ProductListActivity::class.java).start()
        }
        buttonLocation.setOnClickListener {
            loadActivity(LocationActivity::class.java).start()
        }
        buttonNavigationBar.setOnClickListener {
            loadActivity(NavigationActivity::class.java).start()
        }
        buttonBitcoin.setOnClickListener {
            loadActivity(BitCoinActivity::class.java).start()
        }
        buttonBottomBar.setOnClickListener {
            loadActivity(BottomBarActivity::class.java).start()
        }
    }
}