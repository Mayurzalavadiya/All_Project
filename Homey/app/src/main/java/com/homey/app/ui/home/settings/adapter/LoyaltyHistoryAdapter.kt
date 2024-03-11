package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.ListViewLoyaltyHistoryItemBinding
import com.homey.app.ui.home.settings.model.LoyaltyData
import com.homey.app.utils.setDrawableColor


class LoyaltyHistoryAdapter :
    RecyclerView.Adapter<LoyaltyHistoryAdapter.ViewHolder>() {

    private val itemList = mutableListOf<LoyaltyData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<LoyaltyData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewLoyaltyHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LoyaltyData) = with(binding) {
            textviewId.text = item.id
            textviewDate.text = item.date
            textviewHotelName.text = item.hotelName

            if (item.price.toInt() >= 0) {
                textviewPoint.text = "+ ${item.price}"
                textviewPoint.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
                textviewPoint.setDrawableColor(R.color.colorPrimary)
            } else {
                textviewPoint.text = item.price
                textviewPoint.setTextColor(ContextCompat.getColor(itemView.context, R.color.Red))
                textviewPoint.setDrawableColor(R.color.Red)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewLoyaltyHistoryItemBinding.inflate(
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

}