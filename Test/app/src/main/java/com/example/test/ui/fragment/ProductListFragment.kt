package com.example.test.ui.fragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.pojo.MyProducts
import com.example.test.databinding.FragmentProductListBinding
import com.example.test.ui.adapter.ProductAdapter
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.base.Const


class ProductListFragment : BaseFragment<FragmentProductListBinding>() {

    private val productAdapter by lazy { ProductAdapter() }

    companion object {
        val productList = mutableListOf<MyProducts>()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentProductListBinding {
        return FragmentProductListBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() {
        resultListener()
        setAdapter()
        setClickListener()
        addData()
    }

    private fun resultListener() {
        requireActivity().supportFragmentManager
            .setFragmentResultListener(Const.DATA, requireActivity()) { _, bundle ->
                val travelTitle = bundle.getString(Const.TRAVEL_TITLE)
                val description = bundle.getString(Const.DESCRIPTION)
                val date = bundle.getString(Const.DATE)
                val price = bundle.getString(Const.PRICE)
                productList.add(MyProducts(travelTitle, date, description, price))
            }
    }

    private fun addData() {
        productAdapter.clear()
        productAdapter.addItem(productList)
    }

    private fun setAdapter() = with(binding) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        recyclerView.addItemDecoration(ItemDecorate())
        recyclerView.adapter = productAdapter
    }

    private fun setClickListener() = with(binding) {
        textViewAdd.setOnClickListener {
            loadFragment()
        }
        imageViewAdd.setOnClickListener {
            loadFragment()
        }
    }
    private fun loadFragment(){
        loadFragment(AddProductFragment())
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._7dp)
        }

    }

}