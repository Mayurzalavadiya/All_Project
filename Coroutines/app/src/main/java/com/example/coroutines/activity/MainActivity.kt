package com.example.coroutines.activity

import android.os.Bundle
import com.example.coroutines.coroutine.CoroutineActivity
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.threads.ThreadActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonCoroutine.setOnClickListener {
            moveToNextScreen(this@MainActivity, CoroutineActivity::class.java)
        }

        buttonThread.setOnClickListener {
            moveToNextScreen(this@MainActivity,ThreadActivity::class.java)
            savePrefsVal(IS_LOGIN,true)
        }

    }
}