package com.homey.app.ui.home.myBooking.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.homey.app.R
import com.homey.app.databinding.FragmentMyBookingBinding
import com.homey.app.ui.activity.IsolatedActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.myBooking.model.MyBookingData
import com.homey.app.ui.home.myBooking.adapter.BookingListAdapter
import com.homey.app.utils.Keys
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyBookingFragment : BaseFragment<FragmentMyBookingBinding>() {

    lateinit var bookingListAdapter: BookingListAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentMyBookingBinding {
        return FragmentMyBookingBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        titleBar(requireActivity(), R.color.White, true)
        setUpAdapter()
    }


    private fun setUpAdapter() = with(binding) {
        bookingListAdapter = BookingListAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        bookingListAdapter.addItem(getBookingList())
        recyclerview.adapter = bookingListAdapter
        bookingListAdapter.onClick(::callBack)
    }


    fun getBookingList(): MutableList<MyBookingData> {
        return mutableListOf<MyBookingData>().apply {
            add(MyBookingData("Upcoming"))
            add(MyBookingData("Completed"))
            add(MyBookingData("Cancelled"))
            add(MyBookingData("Completed"))
            add(MyBookingData("Completed"))
            add(MyBookingData("Cancelled"))
        }
    }

    fun callBack(status: String) {
        navigator.loadActivity(IsolatedActivity::class.java,BookingDetailsFragment::class.java).addBundle(Bundle().apply {
            putString(Keys.STATUS, status)
        }).start()
    }


}