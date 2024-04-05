package com.example.fragmentdemo.task5.activity

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityViewPager2Binding
import com.example.fragmentdemo.task5.adapter.ViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPager2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPager2Binding


    private val viewPager2Adapter by lazy { ViewPager2Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setTabName()
//        addTabsDividers(binding.tabLayout,R.color.colorRed,3,5)
        addTabDivider()
        callBack()

    }


    private fun setTabName() = with(binding) {
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    private fun setAdapter() = with(binding) {
//        viewpager2.isUserInputEnabled = false
        viewPager2Adapter.addItem(getItem())
        viewpager2.adapter = viewPager2Adapter
    }

    //firstWay
    fun addTabDivider() = with(binding) {
        val linearLayout = tabLayout.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        val drawable: Drawable? =
            ContextCompat.getDrawable(this@ViewPager2Activity, R.drawable.tab_divider)
        linearLayout.dividerDrawable = drawable
    }

    //Second way
    fun addTabsDividers(
        tabLayout: TabLayout,
        @ColorRes divColorRes: Int,
        divWidthDP: Int,
        divHeightDP: Int
    ) {
        val root = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(tabLayout.context.getColor(divColorRes))
            drawable.setSize(divWidthDP, divHeightDP)
            root.setDividerPadding(20)
            root.dividerDrawable = drawable
        }
    }

    private fun callBack() = with(binding) {
        viewpager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

            }
        })
    }

    private fun getItem(): MutableList<Int> {
        return mutableListOf<Int>().apply {
            add(R.drawable.image1)
            add(R.drawable.image2)
            add(R.drawable.image3)
            add(R.drawable.image4)
            add(R.drawable.image5)
            add(R.drawable.image6)
            add(R.drawable.image7)
            add(R.drawable.image8)
            add(R.drawable.image9)
            add(R.drawable.image10)
        }
    }

}