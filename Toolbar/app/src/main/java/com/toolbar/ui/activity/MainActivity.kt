package com.toolbar.ui.activity

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.sample26.ui.base.BaseActivity
import com.toolbar.R
import com.toolbar.databinding.ActivityMainBinding
import com.toolbar.ui.fragment.FirstFragment


class MainActivity : BaseActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding


    override fun createView(): View {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        return activityMainBinding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.fragmentContainer

    override fun onBindActivity() = with(activityMainBinding) {
        setClickListener()
        setSupportActionBar(toolbar.toolbar)
        val firstFragment = FirstFragment()
        loadFragment(firstFragment, isAllowBackStack = false)
    }

    private fun setClickListener() = with(activityMainBinding) {
//        getMyPref().savePrefsVal()
    }

}