package com.homehaven.app.ui.home.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.homehaven.app.R
import com.homehaven.app.databinding.FragmentMyAccountBinding
import com.homehaven.app.ui.base.BaseFragment
import com.homehaven.app.ui.home.model.MyAccountData
import com.homehaven.app.utils.titleBar
import com.rapido.app.ui.auth.adapter.MyAccountAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountFragment : BaseFragment<FragmentMyAccountBinding>() {

    val myAccountAdapter by lazy { MyAccountAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { titleBar(it, R.color.colorPrimary) }
    }
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentMyAccountBinding {
        return FragmentMyAccountBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {
        setUpAdapter()
    }

    private fun setUpAdapter() = with(binding.recyclerview) {
        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        myAccountAdapter.addItem(getGeneralData())
        adapter = myAccountAdapter
        //        addressRecyclerAdapter.addItem()
    }

    fun getGeneralData():MutableList<MyAccountData>{
        return mutableListOf<MyAccountData>().apply {
            add(MyAccountData(R.drawable.receipt_icon,"Transaction"))
            add(MyAccountData(R.drawable.love_icon,"Wishlist"))
            add(MyAccountData(R.drawable.bookmark_icon,"Saved Address"))
            add(MyAccountData(R.drawable.payment_icon,"Payment Methods"))
            add(MyAccountData(R.drawable.notification_icon,"Notification"))
            add(MyAccountData(R.drawable.icon_lock,"Security"))
        }
    }

}