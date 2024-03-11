package com.mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mvvm.R
import com.mvvm.databinding.ActivityTaskBinding
import com.mvvm.ui.base.BaseActivity
import com.mvvm.ui.dagger.activity.LoginActivity
import com.mvvm.ui.demoDagger.activity.SharePrefActivity

class TaskActivity : BaseActivity() {

    private lateinit var binding: ActivityTaskBinding
    override fun createView(): View {
        binding = ActivityTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonCounter.setOnClickListener {
            moveToNextScreen(MainActivity::class.java)
        }
        buttonApiCall.setOnClickListener {
            moveToNextScreen(CountryListActivity::class.java)
        }
        buttonDagger.setOnClickListener {
            moveToNextScreen(LoginActivity::class.java)
        }
        buttonPrefTask.setOnClickListener {
            moveToNextScreen(SharePrefActivity::class.java)
        }
    }

}