package com.example.fragmentdemo.task8.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentdemo.task8.pojo.FragmentData

class MyCustomPagerAdapter(val activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val fragmentList = mutableListOf<FragmentData>()

    fun addFragment(fragment: MutableList<FragmentData>) {
        this.fragmentList.addAll(fragment)
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position].fragment

    fun getFragmentLIst(): MutableList<FragmentData> = fragmentList
}