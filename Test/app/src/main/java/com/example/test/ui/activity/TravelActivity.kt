package com.example.test.ui.activity

import android.view.View
import com.example.test.R
import com.example.test.databinding.ActivityTravelBinding
import com.example.test.ui.base.BaseActivity
import com.example.test.ui.fragment.ProductListFragment

class TravelActivity : BaseActivity() {

    private lateinit var binding: ActivityTravelBinding
    override fun createView(): View {
        binding = ActivityTravelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.fragmentContainer


    override fun onBindActivity() {
        loadFragment(ProductListFragment(),isAllowBackStack = false)
    }

}