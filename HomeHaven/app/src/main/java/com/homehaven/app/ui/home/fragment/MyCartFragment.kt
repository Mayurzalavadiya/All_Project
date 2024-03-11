package com.homehaven.app.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.homehaven.app.R
import com.homehaven.app.databinding.FragmentMyCartBinding
import com.homehaven.app.ui.base.BaseFragment
import com.homehaven.app.ui.home.model.MyCartTData
import com.homehaven.app.ui.home.model.SpecialOfferData
import com.homehaven.app.utils.titleBar
import com.rapido.app.ui.auth.adapter.MyCartAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCartFragment : BaseFragment<FragmentMyCartBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { titleBar(it, R.color.ColorBackground,true) }
    }
    val myCartAdapter by lazy { MyCartAdapter() }
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentMyCartBinding {
        return FragmentMyCartBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {
        setUpAdapter()
    }

    private fun setUpAdapter() = with(binding.recyclerview) {
        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        myCartAdapter.addItem(getData())
        adapter = myCartAdapter
        //        addressRecyclerAdapter.addItem()
    }

    fun getData():MutableList<MyCartTData>{
        return mutableListOf<MyCartTData>().apply {
            add(
                MyCartTData(
                    R.drawable.product_1,
                    "45",
                    "EKERÖ",
                    "230.00",
                    "512.58",
                    "Yellow"
                )
            )
            add(
                MyCartTData(
                    R.drawable.product_2,
                    "45",
                    "STRANDMON",
                    "274.13",
                    "856.60",
                    "Grey"
                )
            )
            add(
                MyCartTData(
                    R.drawable.product_3,
                    "45",
                    "PLATTLÄNS",
                    "24.99",
                    "69.99",
                    "Yellow"
                )
            )
            add(
                MyCartTData(
                    R.drawable.product_4,
                    "45",
                    "MALM",
                    "139.99",
                    "199.99",
                    "Black"
                )
            )
        }
    }
}
