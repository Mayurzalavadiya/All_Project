package com.homey.app.ui.home.myBooking.fragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.FragmentUpcomingBookingBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.myBooking.adapter.RoomDetailsAdapter
import com.homey.app.ui.home.myBooking.model.RoomDetailData
import com.homey.app.utils.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingDetailsFragment : BaseFragment<FragmentUpcomingBookingBinding>() {

    lateinit var roomDetailsAdapter: RoomDetailsAdapter
    private var status: String? = null
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentUpcomingBookingBinding {
        return FragmentUpcomingBookingBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        getData()
        setData()
        setUpAdapter()
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener { requireActivity().finish() }

        constraintCancelBooking.setOnClickListener {
            navigator.load(CancleBookingFragment::class.java).replace(true)
        }
        constraintRateReview.setOnClickListener {
            navigator.load(CancleBookingFragment::class.java).replace(true)
        }
    }

    private fun setData() = with(binding) {
        when (status) {
            "Upcoming" -> {
                textviewStatus.text = status
                textviewStatus.setBackgroundTintList(
                    ContextCompat.getColorStateList(textviewStatus.context, R.color.Orange)
                        ?.withAlpha(20)
                )
            }

            "Completed" -> {
                textviewStatus.text = status
                setColor(R.color.lightGreen, true)
                constraintSpecialRequest.visibility = View.GONE
                constraintCancelBooking.visibility = View.GONE
                constraintRateReview.visibility = View.VISIBLE
            }

            "Cancelled" -> {
                textviewStatus.text = status
                setColor(R.color.Red, true)
                constraintSpecialRequest.visibility = View.GONE
                constraintCancelBooking.visibility = View.GONE
                constraintCancelReason.visibility = View.VISIBLE
            }
        }
    }

    fun setColor(color: Int, isUpcoming: Boolean = false) = with(binding) {
        textviewStatus.setTextColor(ContextCompat.getColor(requireActivity(), color))
        textviewStatus.setBackgroundTintList(
            ContextCompat.getColorStateList(
                textviewStatus.context,
                color
            )?.withAlpha(20)
        )
        if (isUpcoming) {
            textViewCheckIn.setTextColor(ContextCompat.getColor(requireActivity(), R.color.Gray))
            textViewCheckOut.setTextColor(ContextCompat.getColor(requireActivity(), R.color.Gray))
            textViewStartDate.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.PrimaryBlack
                )
            )
            textViewEndDate.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.PrimaryBlack
                )
            )
            imageviewLine.setImageTintList(
                ContextCompat.getColorStateList(
                    requireActivity(),
                    R.color.Gray
                )
            )
            textViewArrival.setTextColor(ContextCompat.getColor(requireActivity(), R.color.Gray))
            constraintDate.setBackgroundTintList(
                ContextCompat.getColorStateList(
                    requireActivity(),
                    R.color.lightWhite
                )
            )
            imageviewPolygon.setImageTintList(
                ContextCompat.getColorStateList(
                    requireActivity(),
                    R.color.lightWhite
                )
            )
        }
    }

    private fun getData() {
        arguments?.let {
            status = it.getString(Keys.STATUS)
        }
    }

    private fun setUpAdapter() = with(binding) {
        roomDetailsAdapter = RoomDetailsAdapter()
        recyclerView.layoutManager =
            GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false)
        roomDetailsAdapter.addItem(getRoomDetailList())
        recyclerView.addItemDecoration(ItemDecorate())
        recyclerView.adapter = roomDetailsAdapter
    }

    fun getRoomDetailList(): MutableList<RoomDetailData> {
        return mutableListOf<RoomDetailData>().apply {
            add(RoomDetailData(R.drawable.person_group_icon, "2 Adults"))
            add(RoomDetailData(R.drawable.person_group_icon, "2 Child "))
            add(RoomDetailData(R.drawable.rooms_icon, "2 Rooms "))
            add(RoomDetailData(R.drawable.single_bed_icon, "Single Bed"))
            add(RoomDetailData(R.drawable.standard_icon, "Standard"))
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