package com.example.listviewdemo.task5.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.FragmentSelectItemsBinding
import com.example.listviewdemo.task4.pojo.ProductItemData
import com.example.listviewdemo.task5.adapter.ItemSelectAdapter
import com.example.listviewdemo.task5.adapter.MultiSelectAdapter
import com.example.listviewdemo.task5.pojo.MultiSelectData

class SelectItemsFragment : Fragment() {

    private lateinit var binding: FragmentSelectItemsBinding
    private var products: ArrayList<MultiSelectData>? = null
    private val multiSelectAdapter by lazy { ItemSelectAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            products = if (Build.VERSION.SDK_INT >= 33) {
                 requireArguments().getParcelableArrayList("SelectedData", MultiSelectData::class.java)
            } else {
                requireArguments().getParcelableArrayList("SelectedData")
            }
        }

        Log.e("TAG", "onCreate: $products", )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectItemsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItemAdapter(products)
    }

    private fun setItemAdapter(products: java.util.ArrayList<MultiSelectData>?) = with(binding) {
        recyclerViewSelectItem.adapter = multiSelectAdapter
        recyclerViewSelectItem.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        multiSelectAdapter.addItem(products)
    }


}