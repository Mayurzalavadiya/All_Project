package com.example.fragmentdemo.task4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragmentdemo.databinding.ActivityFragmentStatePagerBinding
import com.example.fragmentdemo.task4.adapter.FragmentStatePagerAdapter
import com.example.fragmentdemo.task4.fragment.SecondFragment

class FragmentStatePagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentStatePagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentStatePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewPager()
    }

    private fun setViewPager() = with(binding) {
        viewPager.adapter = FragmentStatePagerAdapter(getFragments(), supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun getFragments(): MutableList<Fragment> {
        return mutableListOf<Fragment>().apply {
            for (i in 1..50) {
                add(SecondFragment.fragmentBundle("Fragment $i"))
            }
        }
    }

}