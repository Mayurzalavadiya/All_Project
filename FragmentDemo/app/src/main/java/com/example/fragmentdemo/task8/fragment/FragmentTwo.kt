package com.example.fragmentdemo.task8.fragment

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentTwoBinding
import com.example.fragmentdemo.task8.adapter.RecyclerAdapter
import com.example.fragmentdemo.task8.pojo.ExploreData
import com.example.fragmentdemo.task8.pojo.FragmentData

class FragmentTwo : Fragment() {

    private lateinit var binding: FragmentTwoBinding

    private val recyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() = with(binding) {
        recyclerAdapter.addItem(getItem())
        recyclerView.addItemDecoration(ItemDecorate())
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerAdapter
    }

    private fun getItem(): MutableList<ExploreData> {
        return mutableListOf<ExploreData>().apply {
            add(ExploreData("Participated"))
            add(ExploreData("Explorer"))
            add(ExploreData("Participated"))
            add(ExploreData(getString(R.string.super_saving)))
            add(ExploreData(getString(R.string.super_saving)))
            add(ExploreData(getString(R.string.super_saving)))
            add(ExploreData(getString(R.string.super_saving)))
            add(ExploreData(getString(R.string.super_saving)))
            add(ExploreData(getString(R.string.super_saving)))
            add(ExploreData(getString(R.string.super_saving)))

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

            outRect.top = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.left = resources.getDimensionPixelSize(R.dimen._15dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._15dp)
        }

    }
}