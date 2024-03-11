package com.example.sample26.activity

import android.os.Bundle
import android.view.View
import com.example.sample26.R
import com.example.sample26.databinding.ActivityMainBinding
import com.example.sample26.fragment.OnboardingFragment
import com.example.sample26.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        window.statusBarColor = getColor( R.color.blue)
        setContentView(binding.root)

//        val onboardingFragment = OnboardingFragment()
//        onboardingFragment.arguments = Bundle().apply {
//            putString(
//                "Title",
//                getString(R.string.your_order_at_your_door_in_minutes_1)
//            )
//            putInt("Count", 0)
//        }
    }

    override fun createView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int =R.id.fragmentContainer

    override fun onBindActivity() {
        val onboardingFragment = OnboardingFragment()
        onboardingFragment.arguments = Bundle().apply {
            putString(
                "Title",
                getString(R.string.your_order_at_your_door_in_minutes_1)
            )
            putInt("Count", 0)
        }
       loadFragment(onboardingFragment, isAllowBackStack = false)
    }
}