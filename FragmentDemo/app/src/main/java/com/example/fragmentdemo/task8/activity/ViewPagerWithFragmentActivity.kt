package com.example.fragmentdemo.task8.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentdemo.databinding.ActivityViewPagerWithFragmentBinding
import com.example.fragmentdemo.task8.adapter.MyCustomPagerAdapter
import com.example.fragmentdemo.task8.fragment.FragmentOne
import com.example.fragmentdemo.task8.fragment.FragmentThird
import com.example.fragmentdemo.task8.fragment.FragmentTwo
import com.example.fragmentdemo.task8.pojo.FragmentData
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerWithFragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerWithFragmentBinding

    private val myCustomPagerAdapter by lazy { MyCustomPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerWithFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()
    }

    private fun setUpViewPager() = with(binding) {

        //View Pager
        myCustomPagerAdapter.addFragment(getItem())
        viewPager.adapter = myCustomPagerAdapter

        //tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text =
//            when(position){
//                0-> "Participated"
//                1-> "Explorer"
//                2-> "Participated"
//                else -> ""
//            }
            tab.text = "Tab $position"
            val list = myCustomPagerAdapter.getFragmentLIst()
            tab.text = list[position].name
        }.attach()
    }

    private fun getItem(): MutableList<FragmentData> {
        return mutableListOf<FragmentData>().apply {
            add(FragmentData(FragmentOne(), "Participated"))
            add(FragmentData(FragmentTwo(), "Explorer"))
            add(FragmentData(FragmentThird(), "Participated"))
        }
    }


}
