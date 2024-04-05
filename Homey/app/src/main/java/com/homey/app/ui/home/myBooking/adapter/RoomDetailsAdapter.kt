package com.homey.app.ui.home.myBooking.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.ListViewRoombookingItemBinding
import com.homey.app.ui.home.myBooking.model.RoomDetailData
import kotlin.math.log


class RoomDetailsAdapter :
    RecyclerView.Adapter<RoomDetailsAdapter.ViewHolder>() {

    private val itemList = mutableListOf<RoomDetailData>()
    lateinit var callBack: (Boolean) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<RoomDetailData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewRoombookingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RoomDetailData) = with(binding) {
            if ((layoutPosition + 1) % 3 == 0) {
                viewEnd.visibility = View.GONE
            } else {
                viewEnd.visibility = View.VISIBLE
            }
            if (itemList.size - 1 == layoutPosition) {
                viewEnd.visibility = View.GONE
            }

        imageviewIcon.setImageResource(item.icon)
        textviewDetail.text = item.name

    }


}


override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
        ListViewRoombookingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}

override fun getItemCount(): Int = itemList.size

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(itemList[position])
}


fun onClick(kFunction1: (Boolean) -> Unit) {
    this.callBack = kFunction1
}
}