package com.homey.app.ui.home.blogs.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.homey.app.ui.home.blogs.model.BlogData

class BlogAdapter(val activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragmentList = mutableListOf<BlogData>()

    fun addFragment(fragment: MutableList<BlogData>) {
        this.fragmentList.addAll(fragment)
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position].fragment

    fun getFragmentList(): MutableList<BlogData> = fragmentList
}