package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.homey.app.databinding.FragmentPaymentMethodBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.PaymentMethodAdapter
import com.homey.app.ui.home.settings.model.AddMoneyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodFragment : BaseFragment<FragmentPaymentMethodBinding>() {

    lateinit var paymentMethodAdapter: PaymentMethodAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentPaymentMethodBinding {
        return FragmentPaymentMethodBinding.inflate(inflater, container, attachToRoot)
    }


    override fun bindData() {
        setClickListener()
        setUpAdapter()
    }


    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
        textviewAddNew.setOnClickListener { setDialog() }
    }

    private fun setUpAdapter() = with(binding) {
        paymentMethodAdapter = PaymentMethodAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        paymentMethodAdapter.addItem(getAddMoneyData())
        recyclerview.adapter = paymentMethodAdapter
    }

    private fun setDialog() {
        val addCardBottomSheetFragment = AddCardBottomSheetFragment()
        addCardBottomSheetFragment.show(childFragmentManager, addCardBottomSheetFragment.tag)
        addCardBottomSheetFragment.callBack(::callBack)
    }

    fun getAddMoneyData(): MutableList<AddMoneyData> = mutableListOf<AddMoneyData>().apply {
        add(AddMoneyData("1234567891234567"))
        add(AddMoneyData("1234567891234567"))
        add(AddMoneyData("1234567891234567"))
    }

    fun callBack(item:AddMoneyData){
        paymentMethodAdapter.updateItem(item)
    }
}