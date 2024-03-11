package com.homey.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.homey.app.R
import com.homey.app.databinding.FragmentOnBoardingBinding
import com.homey.app.ui.activity.AuthActivity
import com.homey.app.ui.auth.adapter.IndicatorAdapter
import com.homey.app.ui.auth.adapter.OnBoardingAdapter
import com.homey.app.ui.auth.model.OnBoardingData
import com.homey.app.ui.base.BaseFragment
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    private val onBoardingAdapter by lazy { OnBoardingAdapter() }
    private lateinit var indicatorAdapter: IndicatorAdapter

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean
    ): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(inflater, container, attachToRoot)

    }

    override fun bindData() {
        titleBar(requireActivity(),R.color.White,true)
        setClickListener()
        setUpAdapter()
        callBack()

    }

    private fun setClickListener() = with(binding) {


        buttonSkip.setOnClickListener {
            if (viewPager.currentItem != getItemList().lastIndex) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                navigator.loadActivity(AuthActivity::class.java).byFinishingAll().start()
                activity?.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            }
        }
    }

    private fun setUpAdapter() = with(binding) {
        //viewPager
        viewPager.isUserInputEnabled = false
        onBoardingAdapter.addItem(getItemList())
        viewPager.adapter = onBoardingAdapter

        //recyclerview Indicator
        indicatorAdapter = IndicatorAdapter()
        recyclerViewIndicator.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        indicatorAdapter.addItem(getItemList().size)
        recyclerViewIndicator.adapter = indicatorAdapter
    }

    private fun callBack() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorAdapter.setPosition(position)
                if (position == getItemList().lastIndex) {
                    buttonSkip.apply {
                        setBackgroundResource(R.drawable.bg_white_10px)
                        setTextColor(
                            ContextCompat.getColor(
                                requireActivity(), R.color.colorPrimary
                            )
                        )
                    }

                } else {
                    buttonSkip.apply {
                        setBackgroundResource(R.drawable.bg_colorprimary_10px)
                        setTextColor(ContextCompat.getColor(requireActivity(), R.color.White))
                    }
                }
            }
        })
    }

    fun getItemList(): MutableList<OnBoardingData> {
        return mutableListOf<OnBoardingData>().apply {
            add(
                OnBoardingData(
                    R.drawable.group_1,
                    "Find the best\n" + "place to stay & make a memorable.",
                )
            )
            add(
                OnBoardingData(
                    R.drawable.group_2,
                    "Choose Hotel and the dates of your Check-in and Check-out",
                )
            )
            add(
                OnBoardingData(
                    R.drawable.group_3,
                    "Select Rooms, Adults, and Children to make a reservation",
                )
            )
        }
    }
}