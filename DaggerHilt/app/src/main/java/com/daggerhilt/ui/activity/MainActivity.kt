package com.daggerhilt.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daggerhilt.R
import com.daggerhilt.databinding.ActivityMainBinding
import com.daggerhilt.ui.base.BaseActivity
import com.daggerhilt.ui.counter.activity.MyCounterActivity
import com.daggerhilt.ui.singleton.SharePrefActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun createView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonCountryList.setOnClickListener {
            moveToNextScreen(CountryListActivity::class.java)
        }

        buttonSharePref.setOnClickListener {
            moveToNextScreen(SharePrefActivity::class.java)
        }
        buttonCounter.setOnClickListener {
            moveToNextScreen(MyCounterActivity::class.java)
        }

    }
}