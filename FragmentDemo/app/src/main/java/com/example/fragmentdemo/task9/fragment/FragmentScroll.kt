package com.example.fragmentdemo.task9.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentScrollBinding
import com.example.fragmentdemo.task8.adapter.ViewpagerAdapter

class FragmentScroll : Fragment() {

    private lateinit var binding: FragmentScrollBinding

    private val viewpagerAdapter by
    lazy { ViewpagerAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrollBinding.inflate(layoutInflater, container, false)
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