package com.example.test.ui.auth.activity

import android.view.View
import com.example.test.R
import com.example.test.databinding.ActivityAuthBinding
import com.example.test.ui.auth.fragment.LoginFragment
import com.example.test.ui.base.BaseActivity

class AuthActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthBinding
    override fun createView(): View {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.fragmentContainer

    override fun onBindActivity() {
      loadFragment(LoginFragment(), isAllowBackStack = false)
    }

}