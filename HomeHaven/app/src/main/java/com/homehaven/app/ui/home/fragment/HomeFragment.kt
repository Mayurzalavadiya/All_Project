package com.homehaven.app.ui.home.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.homehaven.app.R
import com.homehaven.app.databinding.FragmentHomeBinding
import com.homehaven.app.ui.activity.IsolatedActivity
import com.homehaven.app.ui.auth.adapter.IndicatorAdapter
import com.homehaven.app.ui.base.BaseFragment
import com.homehaven.app.ui.home.Interfaces.ClickListener
import com.homehaven.app.ui.home.adapter.BannerAdapter
import com.homehaven.app.ui.home.adapter.SpecialOfferAdapter
import com.homehaven.app.ui.home.model.SpecialOfferData
import com.homehaven.app.utils.Key
import com.homehaven.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), ClickListener {

    private lateinit var indicatorAdapter: IndicatorAdapter
    val banAdapter by lazy { BannerAdapter() }
    val specialOfferAdapter by lazy { SpecialOfferAdapter(this) }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { titleBar(it, R.color.ColorBackground, true) }
    }

    override fun bindData() {
        setUpAdapter()
        callBack()
    }

    private fun setUpAdapter() = with(binding) {
        //viewPager
        banAdapter.addItem(getBanner())
        viewPagerBanner.adapter = banAdapter


        //for indicator
        indicatorAdapter = IndicatorAdapter()
        recyclerviewIndicator.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        indicatorAdapter.addItem(getBanner().size)
        recyclerviewIndicator.adapter = indicatorAdapter

        //recyclerViewSpecialOffer
        recyclerViewSpecialOffer.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSpecialOffer.addItemDecoration(ItemDecorate())
        specialOfferAdapter.addItem(getSpecialList())
        recyclerViewSpecialOffer.adapter = specialOfferAdapter
    }

    private fun callBack() = with(binding) {
        viewPagerBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorAdapter.setPosition(position)
            }

        })
    }

    fun getBanner(): MutableList<Int> {
        return mutableListOf<Int>().apply {
            add(R.drawable.banner)
            add(R.drawable.banner)
            add(R.drawable.banner)

        }
    }

    fun getSpecialList(): MutableList<SpecialOfferData> {
        return mutableListOf<SpecialOfferData>().apply {
            add(
                SpecialOfferData(
                    R.drawable.product_1,
                    "45",
                    "EKERÖ",
                    "230.00",
                    "512.58",
                    "4.9 (256)"
                )
            )
            add(
                SpecialOfferData(
                    R.drawable.product_2,
                    "45",
                    "STRANDMON",
                    "274.13",
                    "856.60",
                    "4.8 (128)"
                )
            )
            add(
                SpecialOfferData(
                    R.drawable.product_3,
                    "45",
                    "PLATTLÄNS",
                    "24.99",
                    "69.99",
                    "4.9 (256)"
                )
            )
            add(
                SpecialOfferData(
                    R.drawable.product_4,
                    "45",
                    "MALM",
                    "139.99",
                    "199.99",
                    "4.9 (256)"
                )
            )
        }
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.left = resources.getDimensionPixelSize(R.dimen._16sdp)
        }

    }

    override fun onClick(position: Int, view: View?) {
        navigator.loadActivity(IsolatedActivity::class.java, ProductDetailFragment::class.java)
            .addBundle(Bundle().apply { putInt(Key.IMAGE, getSpecialList()[position].image) })
            .start()
    }
}