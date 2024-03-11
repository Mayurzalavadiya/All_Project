package com.mvvm.ui.activity

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mvvm.databinding.ActivityMainBinding
import com.mvvm.ui.base.BaseActivity
import com.mvvm.ui.viewModel.MainActivityViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy { ViewModelProvider(this)[MainActivityViewModel::class.java] }

    override fun createView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() = with(binding) {


        textView.text = viewModel.number.toString()

        button.setOnClickListener {
            viewModel.addOne()
            textView.text = viewModel.number.toString()
        }
    }
}