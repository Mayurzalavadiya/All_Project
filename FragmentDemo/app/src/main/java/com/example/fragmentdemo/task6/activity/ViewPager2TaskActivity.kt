package com.example.fragmentdemo.task6.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityViewPager2TaskBinding
import com.example.fragmentdemo.mainActivity.BaseActivity
import com.example.fragmentdemo.task6.adapter.ViewPager2TaskAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2TaskActivity : BaseActivity() {

    private lateinit var binding: ActivityViewPager2TaskBinding

    private val viewPager2TaskAdapter by lazy { ViewPager2TaskAdapter(getItem()) }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2TaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()

    }


    private fun movePrevious() = with(binding) {
        //it doesn't matter if you're already in the first item
        viewpager2.setCurrentItem(viewpager2.currentItem + 1, true)
    }

    private fun setAdapter() = with(binding) {
        viewpager2.adapter = viewPager2TaskAdapter
        viewpager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //tabLayout
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            tab.text = "Tab $position"
        }.attach()
    }

}


private fun getItem(): ArrayList<Int> {
    return arrayListOf<Int>().apply {
        add(R.drawable.image1)
        add(R.drawable.image2)
//        add(R.drawable.image3)
//        add(R.drawable.image4)
//        add(R.drawable.image5)
//        add(R.drawable.image6)
//        add(R.drawable.image7)
//        add(R.drawable.image8)
//        add(R.drawable.image9)
//        add(R.drawable.image10)
    }
}



