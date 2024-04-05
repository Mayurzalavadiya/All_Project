package com.example.fragmentdemo.task8.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentThirdBinding
import com.example.fragmentdemo.task8.adapter.ViewpagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FragmentThird : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    private val viewpagerAdapter by
    lazy { ViewpagerAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    private fun setUpViewPager() = with(binding) {

        //View Pager
        viewpagerAdapter.addItem(getItem())
        viewPager.adapter = viewpagerAdapter

        //tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    private fun getItem(): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            add(R.drawable.image1)
            add(R.drawable.image2)
            add(R.drawable.image3)
            add(R.drawable.image4)
            add(R.drawable.image5)
            add(R.drawable.image_3)
            add(R.drawable.image6)
            add(R.drawable.image7)
            add(R.drawable.image9)
            add(R.drawable.image10)
        }
    }

}