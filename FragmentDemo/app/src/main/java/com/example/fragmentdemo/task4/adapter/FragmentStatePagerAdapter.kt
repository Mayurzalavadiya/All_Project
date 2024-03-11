package com.example.fragmentdemo.task4.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fragmentdemo.R
import com.example.fragmentdemo.task4.fragment.FirstFragment

class FragmentStatePagerAdapter(private val items: MutableList<Fragment>, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = items.size


    override fun getItem(position: Int): Fragment {
        return items[position]

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab $position"
    }
}