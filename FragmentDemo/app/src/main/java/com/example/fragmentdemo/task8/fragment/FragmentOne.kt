package com.example.fragmentdemo.task8.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentOneBinding
import com.example.fragmentdemo.task8.adapter.StaggeredAdapter

class FragmentOne : Fragment() {

    private lateinit var binding: FragmentOneBinding

    private val staggeredAdapter by
    lazy { StaggeredAdapter(getItem()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

    }

    private fun setAdapter() = with(binding) {
        val staggered = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewStaggered.layoutManager = staggered
        recyclerViewStaggered.adapter= staggeredAdapter

    }


    private fun getItem(): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            add(R.drawable.image1)
            add(R.drawable.image2)
            add(R.drawable.image3)
            add(R.drawable.image4)
            add(R.drawable.image5)
            add(R.drawable.image6)
            add(R.drawable.image7)
            add(R.drawable.image9)
            add(R.drawable.image10)
        }
    }
}