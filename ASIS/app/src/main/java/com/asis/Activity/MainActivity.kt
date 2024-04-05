package com.asis.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asis.fragment.SplashFragment
import com.asis.R
import com.asis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SplashFragment(), SplashFragment::class.java.simpleName)
            .commit()
    }
}