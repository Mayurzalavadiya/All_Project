package com.example.fragmentdemo.task7.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ActivityViewpagerWithRecyclerBinding
import com.example.fragmentdemo.mainActivity.BaseActivity
import com.example.fragmentdemo.task7.adapter.ViewPagerRecyclerAdapter
import com.example.fragmentdemo.task7.pojo.RecyclerData
import com.example.fragmentdemo.task7.adapter.IndicatorAdapter


class ViewpagerWithRecyclerActivity : BaseActivity() {

    private lateinit var binding: ActivityViewpagerWithRecyclerBinding

    private val indicatorAdapter by lazy { IndicatorAdapter() }

    private val viewPagerRecyclerAdapter by lazy { ViewPagerRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpagerWithRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapter()
        setAdapter()
        callBack()
        setClickListener()

    }

    private fun setAdapter() = with(binding) {
        viewPagerRecyclerAdapter.addItem(getItems())
        viewPager.adapter = viewPagerRecyclerAdapter

        //tabLayout
//        TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
//            tab.view.isClickable = false
//        }.attach()
    }

    private fun setUpAdapter() = with(binding.recyclerview) {
        layoutManager = LinearLayoutManager(
            this@ViewpagerWithRecyclerActivity, LinearLayoutManager.HORIZONTAL, false
        )
        adapter = indicatorAdapter
        indicatorAdapter.addItem(getItems().size)
    }

    private fun setClickListener() = with(binding) {
        textViewBack.setOnClickListener {
            if (viewPager.currentItem != 0) {
                viewPager.setCurrentItem(viewPager.currentItem - 1, true)
            }
        }

        textViewNext.setOnClickListener {
            if (viewPager.currentItem != viewPagerRecyclerAdapter.getList().lastIndex) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else showMessage(this@ViewpagerWithRecyclerActivity, getString(R.string.finish))
        }
    }


    private fun callBack() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorAdapter.setPosition(position)
//                setIndicator(position)
                if (position != 0) {
                    textViewBack.visibility = View.VISIBLE
                } else {
                    textViewBack.visibility = View.INVISIBLE
                }
                if (position == viewPagerRecyclerAdapter.getList().lastIndex) {
                    textViewNext.text = getString(R.string.finish)
                } else {
                    textViewNext.text = getString(R.string.next)
                }
            }

        })
    }


    private fun getItems(): MutableList<RecyclerData> {
        return mutableListOf<RecyclerData>().apply {
            add(
                RecyclerData(
                    R.drawable.ic_baseline_directions_bike_24,
                    "Bike",
                    "This is Bike",
                    R.color.purple_500
                ),
            )
            add(
                RecyclerData(
                    R.drawable.ic_baseline_directions_car_filled_24,
                    "Car",
                    "This is Car",
                    R.color.colorRed
                ),
            )
            add(
                RecyclerData(
                    R.drawable.ic_baseline_train_24, "Train", "This is Train", R.color.colorYellow
                ),
            )
            add(
                RecyclerData(
                    R.drawable.ic_baseline_smartphone_24, "Phone", "This is Phone", R.color.teal_700
                )
            )
            add(
                RecyclerData(
                    R.drawable.ic_baseline_local_airport_24,
                    "Flight",
                    "This is Flight",
                    R.color.blue
                )
            )
        }
    }
}