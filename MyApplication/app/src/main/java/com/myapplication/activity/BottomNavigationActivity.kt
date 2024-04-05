package com.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myapplication.Fragment.AboutFragment
import com.myapplication.Fragment.HomeFragment
import com.myapplication.Fragment.ShareFragment
import com.myapplication.R

class BottomNavigationActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_item1 -> {
                    switchFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_item2 -> {
                    switchFragment(AboutFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_item3 -> {
                    switchFragment(ShareFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottomnavigation)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set the initial fragment when the activity starts
        if (savedInstanceState == null) {
            switchFragment(HomeFragment())
        }
    }
}