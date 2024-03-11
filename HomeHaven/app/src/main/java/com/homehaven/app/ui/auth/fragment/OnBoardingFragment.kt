package com.homehaven.app.ui.auth.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.homehaven.app.R
import com.homehaven.app.databinding.FragmentOnBoardingBinding
import com.homehaven.app.ui.auth.adapter.IndicatorAdapter
import com.homehaven.app.ui.auth.adapter.OnBoardingAdapter
import com.homehaven.app.ui.auth.model.OnBoardingData
import com.homehaven.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    private val onBoardingAdapter by lazy { OnBoardingAdapter() }

    private lateinit var indicatorAdapter: IndicatorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = activity?.window!!
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = ContextCompat.getColor(requireActivity(),android.R.color.transparent)
        }
    }
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(inflater, container, attachToRoot)

    }

    override fun bindData() {
        setClickListener()
        setUpAdapter()
        callBack()

    }

    private fun setClickListener() = with(binding) {
        textviewBack.setOnClickListener {
            if (viewPager.currentItem != 0) {
                viewPager.setCurrentItem(viewPager.currentItem - 1, true)
            }
        }


        buttonNext.setOnClickListener {
            if (viewPager.currentItem != getItemList().lastIndex) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                navigator.load(LoginFragment::class.java).replace(false)
            }
        }
    }


    private fun setUpAdapter() = with(binding) {
        //viewPager
        onBoardingAdapter.addItem(getItemList())
        viewPager.adapter = onBoardingAdapter

        //recyclerview Indicator
        indicatorAdapter = IndicatorAdapter()
        recyclerViewIndicator.adapter = indicatorAdapter
        recyclerViewIndicator.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        indicatorAdapter.addItem(getItemList().size)
    }


    private fun callBack() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorAdapter.setPosition(position)
                if (position != 0) {
                    textviewBack.visibility = View.VISIBLE
                } else {
                    textviewBack.visibility = View.GONE
                }
            }

        })
    }

    fun getItemList(): MutableList<OnBoardingData> {
        return mutableListOf<OnBoardingData>().apply {
            add(
                OnBoardingData(
                    R.drawable.group_1,
                    "Online Home Store and Furniture",
                    "Discover all style and budgets of furniture, appliances, kitchen, and more from 500+ brands in your hand."
                )
            )
            add(
                OnBoardingData(
                    R.drawable.group_2,
                    "Delivery Right to Your Doorstep",
                    "Sit back, and enjoy the convenience of our drivers delivering your order to your doorstep."
                )
            )
            add(
                OnBoardingData(
                    R.drawable.group_3,
                    "Get Support From Our Skilled Team",
                    "If our products don't meet your expectations, we're available 24/7 to assist you."
                )
            )
        }
    }
}