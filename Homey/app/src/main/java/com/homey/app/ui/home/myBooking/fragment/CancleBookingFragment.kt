package com.homey.app.ui.home.myBooking.fragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.FragmentCancleBookingBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.myBooking.adapter.CancleBookingListAdapter
import com.homey.app.ui.home.myBooking.model.MyBookingData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancleBookingFragment : BaseFragment<FragmentCancleBookingBinding>() {

    lateinit var cancleBookingListAdapter: CancleBookingListAdapter
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCancleBookingBinding {
        return FragmentCancleBookingBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setUpAdapter()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener { navigator.goBack() }
        buttonSubmit.setOnClickListener { requireActivity().finish() }
    }

    private fun setUpAdapter() = with(binding) {
        cancleBookingListAdapter = CancleBookingListAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        cancleBookingListAdapter.addItem(getBookingList())
        recyclerView.addItemDecoration(ItemDecorate())
        recyclerView.adapter = cancleBookingListAdapter
        cancleBookingListAdapter.onClick(::callBack)
    }


    private fun getBookingList(): MutableList<MyBookingData> {
        return mutableListOf<MyBookingData>().apply {
            add(MyBookingData("Planned Change"))
            add(MyBookingData("There is no respoonce from Hotel."))
            add(MyBookingData("Mind Change."))
            add(MyBookingData("Food is not available."))
            add(MyBookingData("Other"))
        }
    }

    fun callBack(status: String) {
        if (status == "Other") {
            binding.edittextReview.visibility = View.VISIBLE
        } else {
            binding.edittextReview.visibility = View.GONE
        }
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = resources.getDimensionPixelSize(R.dimen._18sdp)
        }
    }
}