package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.homey.app.databinding.FragmentLoyaltyHistoryBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.LoyaltyHistoryAdapter
import com.homey.app.ui.home.settings.model.LoyaltyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoyaltyHistoryFragment : BaseFragment<FragmentLoyaltyHistoryBinding>() {


    lateinit var loyaltyHistoryAdapter: LoyaltyHistoryAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentLoyaltyHistoryBinding {
        return FragmentLoyaltyHistoryBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setUpAdapter()
        setClickListener()
    }

    private fun setClickListener() = with(binding){
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
    }

    private fun setUpAdapter() = with(binding) {
        loyaltyHistoryAdapter = LoyaltyHistoryAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        loyaltyHistoryAdapter.addItem(getLoyaltyData())
        recyclerview.adapter = loyaltyHistoryAdapter
    }

    fun getLoyaltyData(): MutableList<LoyaltyData> = mutableListOf<LoyaltyData>().apply {
        add(LoyaltyData("#6548651612","17 Mar, 2022 - 03:00 pm","Paris Sand Hotel", "10"))
        add(LoyaltyData("#6548651612","15 Mar, 2022 - 03:00 pm","Paris Sand Hotel", "-20"))
        add(LoyaltyData("#6548651612","12 Mar, 2022 - 03:00 pm","Paris Sand Hotel", "-500"))
        add(LoyaltyData("#6548651612","10 Mar, 2022 - 03:00 pm","Paris Sand Hotel", "100"))
        add(LoyaltyData("#6548651612","17 Feb, 2022 - 03:00 pm","Paris Sand Hotel", "250"))
        add(LoyaltyData("#6548651612","17 Mar, 2022 - 03:00 pm","Paris Sand Hotel", "-300"))
        add(LoyaltyData("#6548651612","17 Mar, 2022 - 03:00 pm","Paris Sand Hotel", "50"))
    }


}