package com.rapido.app.ui.auth.fragment

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.rapido.app.di.DiConstants
import com.rapido.app.ui.auth.adapter.InterestRecyclerAdapter
import com.rapido.app.ui.auth.model.InterestData
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.databinding.FragmentInterestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestFragment : BaseFragment<FragmentInterestBinding>() {


    private val interestRecyclerAdapter by lazy { InterestRecyclerAdapter() }


    private var list: List<String>? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentInterestBinding {
        return FragmentInterestBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        list = arguments?.getString(DiConstants.SEND_LIST_DATA)?.split(", ")
        setAdapter()
        setClickListener()
    }


    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener {
            requireActivity().setResult(Activity.RESULT_CANCELED)
            requireActivity().finish()
        }

        buttonSubmit.setOnClickListener {
            val selectList = interestRecyclerAdapter.getSelectList()
            val intent = Intent()
            intent.putExtra(DiConstants.LIST_DATA, selectList)
            requireActivity().setResult(Activity.RESULT_OK, intent)
            requireActivity().finish()
        }
    }

    private fun setAdapter() = with(binding.recyclerView) {
        val flexboxLayoutManager = FlexboxLayoutManager(requireActivity())
        flexboxLayoutManager.apply {
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
            justifyContent = JustifyContent.FLEX_START
        }
        layoutManager = flexboxLayoutManager


        adapter = interestRecyclerAdapter
        interestRecyclerAdapter.addItem(getList())
    }

    private fun getList(): MutableList<InterestData> {
        val dataList = mutableListOf<InterestData>().apply {
            add(InterestData("Sports"))
            add(InterestData("Gaming"))
            add(InterestData("Technology"))
            add(InterestData("Automotive"))
            add(InterestData("Photography"))
            add(InterestData("Fitness"))
            add(InterestData("Art & Crafts"))
            add(InterestData("Business"))
            add(InterestData("Architecture"))
            add(InterestData("Fashion"))
        }

        list?.let {
            it.forEach { item ->
                for (i in dataList.indices) {
                    if (dataList[i].name == item) {
                        dataList[i].isSelect = true
                    }
                }
            }
        }
        return dataList
    }


}

