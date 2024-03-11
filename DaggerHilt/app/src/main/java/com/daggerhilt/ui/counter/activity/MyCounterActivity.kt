package com.daggerhilt.ui.counter.activity

import android.view.View
import com.daggerhilt.R
import com.daggerhilt.databinding.ActivityMyCounterBinding
import com.daggerhilt.ui.base.BaseActivity
import com.daggerhilt.ui.counter.fragment.FirstFragment


class MyCounterActivity : BaseActivity() {

    private lateinit var binding: ActivityMyCounterBinding

//    @Inject
//    lateinit var myCounter : MyCounter

    override fun createView(): View {
        binding = ActivityMyCounterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.fragmentContainer

    override fun onBindActivity() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        showShackBarMessage(findViewById(android.R.id.content),"")
        loadFragment(FirstFragment(), isAllowBackStack = false)

//        textView.text = myCounter.count.toString()

//        button.setOnClickListener {
//            myCounter.count++
//            textView.text = myCounter.count.toString()
    }
}

