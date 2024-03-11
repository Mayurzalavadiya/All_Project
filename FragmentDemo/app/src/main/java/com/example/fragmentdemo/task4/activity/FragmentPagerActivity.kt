package com.example.fragmentdemo.task4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityFragmentPagerBinding
import com.example.fragmentdemo.task4.adapter.FragmentPagerAdapter
import com.example.fragmentdemo.task4.fragment.FirstFragment

class FragmentPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getFragments()
        setViewPager()
    }

    private fun setViewPager() = with(binding) {
        viewPager.adapter = FragmentPagerAdapter(getFragments(), supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }


    private fun getFragments(): MutableList<Fragment> {
        return (mutableListOf<Fragment>().apply {
            add(FirstFragment.fragmentBundle("Fragment 1 ", R.drawable.image1))
            add(FirstFragment.  fragmentBundle("fragment 2 ", R.drawable.image2))
            add(FirstFragment.fragmentBundle("fragment 3 ", R.drawable.image8))
            add(FirstFragment.fragmentBundle("fragment 4 ", R.drawable.image4))
            add(FirstFragment.fragmentBundle("fragment 5 ", R.drawable.image5))
        })
    }

}