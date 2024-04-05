package com.social.app.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.social.app.R
import com.social.app.databinding.FragmentOnBoardingBinding
import com.social.app.ui.activity.AuthActivity
import com.social.app.ui.base.BaseFragment
import com.social.app.ui.auth.adapter.IndicatorAdapter
import com.social.app.ui.auth.adapter.OnBoardingAdapter
import com.social.app.ui.auth.model.OnBoardingData
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
        setClickListener()
        setUpAdapter()
        callBack()

    }

    private fun setClickListener() = with(binding) {

        imageNext.setOnClickListener {
            navigator.loadActivity(AuthActivity::class.java).byFinishingAll().start()
        }
        btnSkip.setOnClickListener {
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
//        viewPager.isUserInputEnabled = false
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
                btnSkip.isClickable = true
                btnSkip.isEnabled = true
                when (position) {
                    0 -> {
                        imageNext.setImageResource(R.drawable.next_button_1)
                    }

                    1 -> {
                        imageNext.setImageResource(R.drawable.next_button_2)

                    }

                    2 -> {
                        btnSkip.isClickable = false
                        btnSkip.isEnabled = false
                        imageNext.setImageResource(R.drawable.next_button_3)
                    }
                }
            }
        })
    }

    fun getItemList(): MutableList<OnBoardingData> {
        return mutableListOf<OnBoardingData>().apply {
            add(
                OnBoardingData(
                    "Find your perfect\nmatch!",
                    "Join today and start your journey to finding genuine connections.",
                )
            )
            add(
                OnBoardingData(
                    "Find your perfect\nmatch!",
                    "Join today and start your journey to finding genuine connections.",
                )
            )
            add(
                OnBoardingData(
                    "Find your perfect\nmatch!",
                    "Join today and start your journey to finding genuine connections.",
                )
            )
        }
    }
}