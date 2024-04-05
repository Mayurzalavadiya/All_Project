package com.example.fragmentdemo.task9.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentdemo.databinding.ActivityViewPagerScrollBothSideBinding
import com.example.fragmentdemo.task8.adapter.MyCustomPagerAdapter
import com.example.fragmentdemo.task8.pojo.FragmentData
import com.example.fragmentdemo.task9.fragment.FragmentScroll

class ViewPagerScrollBothSideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerScrollBothSideBinding
    private val myCustomPagerAdapter by lazy { MyCustomPagerAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerScrollBothSideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serAdapter()
    }

    private fun serAdapter() = with(binding) {

        //View Pager
        myCustomPagerAdapter.addFragment(getItem())
        viewPager.adapter = myCustomPagerAdapter
    }

    private fun getItem(): MutableList<FragmentData> {
        return mutableListOf<FragmentData>().apply {
            for(i in 0..50) {
                add(FragmentData(FragmentScroll(), "Participated"))
            }
        }
    }
}