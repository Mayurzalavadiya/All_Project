package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.homey.app.databinding.FragmentWalletHistoryBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.WalletHistoryAdapter
import com.homey.app.ui.home.settings.model.LoyaltyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletHistoryFragment : BaseFragment<FragmentWalletHistoryBinding>() {

    lateinit var walletHistoryAdapter: WalletHistoryAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentWalletHistoryBinding {
        return FragmentWalletHistoryBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
        setUpAdapter()
    }

    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }

        textviewAddMoney.setOnClickListener {
            navigator.load(AddMoneyFragment::class.java).replace(true)
        }
    }

    private fun setUpAdapter() = with(binding) {
        walletHistoryAdapter = WalletHistoryAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        walletHistoryAdapter.addItem(getWalletData())
        recyclerview.adapter = walletHistoryAdapter
    }

    fun getWalletData(): MutableList<LoyaltyData> = mutableListOf<LoyaltyData>().apply {
        add(LoyaltyData(null, "17 Mar, 2022 - 03:00 pm", "Paris Sand Hotel", "-100"))
        add(LoyaltyData("#6548651612", "15 Mar, 2022 - 03:00 pm", null, "200"))
        add(LoyaltyData("#6548651612", "12 Mar, 2022 - 03:00 pm", "New Avenue Hotel", "-500"))
        add(LoyaltyData("#6548651612", "10 Mar, 2022 - 03:00 pm", "Paris Sand Hotel", "-100"))
        add(LoyaltyData(null, "17 Feb, 2022 - 03:00 pm", null, "250"))
        add(LoyaltyData("#6548651612", "17 Mar, 2022 - 03:00 pm", "New Avenue Hotel", "-300"))
        add(LoyaltyData(null, "17 Mar, 2022 - 03:00 pm", "Paris Sand Hotel", "50"))
    }

}