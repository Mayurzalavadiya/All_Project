package com.homehaven.app.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import com.homehaven.app.R
import com.homehaven.app.databinding.HomeActivityBinding
import com.homehaven.app.ui.base.BaseActivity
import com.homehaven.app.ui.home.fragment.ChatFragment
import com.homehaven.app.ui.home.fragment.HomeFragment
import com.homehaven.app.ui.home.fragment.MainFragment
import com.homehaven.app.ui.home.fragment.MyAccountFragment
import com.homehaven.app.ui.home.fragment.MyCartFragment
import com.homehaven.app.ui.home.fragment.OrderFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    lateinit var binding: HomeActivityBinding

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment()
        setClickListener()
    }


    private fun loadFragment() {
        load(HomeFragment::class.java).replace(false)

    }

    private fun setClickListener() = with(binding) {
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomHome -> {
                    load(HomeFragment::class.java).replace(false)
                    true
                }

                R.id.bottomMyAccount -> {
                    load(MyAccountFragment::class.java).replace(true)
                    true
                }

                R.id.bottomMyCart -> {
                    load(MyCartFragment::class.java).replace(true)
                    true
                }



                else -> {
                    true
                }
            }
        }
    }


}
