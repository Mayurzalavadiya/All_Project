package com.example.fragmentdemo.task4.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class FragmentPagerAdapter(private val items: MutableList<Fragment>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {


    override fun getCount(): Int = items.size


    override fun getItem(position: Int): Fragment {
        return items[position]
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab $position"
    }
}