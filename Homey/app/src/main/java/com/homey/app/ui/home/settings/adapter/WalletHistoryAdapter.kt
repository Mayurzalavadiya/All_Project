package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.ListViewLoyaltyHistoryItemBinding
import com.homey.app.databinding.ListViewWalletHistoryItemBinding
import com.homey.app.ui.home.settings.model.LoyaltyData
import com.homey.app.utils.setDrawableColor


class WalletHistoryAdapter :
    RecyclerView.Adapter<WalletHistoryAdapter.ViewHolder>() {

    private val itemList = mutableListOf<LoyaltyData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<LoyaltyData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewWalletHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LoyaltyData) = with(binding) {

            textviewDate.text = item.date
            if (item.hotelName != null) {
                textviewHotelName.text = item.hotelName
                textviewHotelName.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.PrimaryBlack
                    )
                )
            } else {
                view.visibility = View.GONE
                textviewHotelName.text =
                    itemView.context.getString(R.string.textview_add_amount_in_wallet)
                textviewHotelName.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.Gray
                    )
                )
            }
            if (item.id != null) {
                textviewId.text = item.id
            } else {
                textviewId.visibility = View.GONE
            }
            if (item.price.toInt() >= 0) {
                textviewPoint.text = "+ \$${item.price}"
                textviewPoint.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorPrimary
                    )
                )
            } else {
                val price = item.price.replace("-", "")
                textviewPoint.text = "- \$$price"
                textviewPoint.setTextColor(ContextCompat.getColor(itemView.context, R.color.Red))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewWalletHistoryItemBinding.inflate(
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