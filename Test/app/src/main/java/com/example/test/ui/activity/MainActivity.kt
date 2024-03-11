package com.example.test.ui.activity

import android.view.View
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.ui.base.BaseActivity
import com.example.test.ui.fragment.MyQuotesFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun createView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.fragmentContainer

    override fun onBindActivity() {
       loadFragment(MyQuotesFragment(), isAllowBackStack = false)
    }
}