package com.daggerhilt.ui.singleton

import android.view.View
import com.daggerhilt.databinding.ActivitySecondBinding
import com.daggerhilt.ui.base.BaseActivity


class SecondActivity : BaseActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun createView(): View {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
//        val application = application
//        if (application is App) {
//            application.appComponent.inject(this)
//        }
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonGet.setOnClickListener {
//            myPref.getStringPref("Email")
//                ?.let { toastGenerate.toast(this@SecondActivity, it) }

            textViewEmail.text = myPref.getStringPref("Email")
        }

    }

}