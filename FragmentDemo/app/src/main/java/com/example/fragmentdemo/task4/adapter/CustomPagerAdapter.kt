package com.example.fragmentdemo.task4.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.fragmentdemo.R

class CustomPagerAdapter(private val items: MutableList<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.custom_viewpager_layout, container, false)

        Log.e("TAG", "instantiateItem: ${items[position]}", )
        view.findViewById<AppCompatImageView>(R.id.imageView).setImageResource(items[position])
        container.addView(view)
        return view
    }

    override fun getCount(): Int = items.size


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab $position"
    }
}