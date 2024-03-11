package com.homey.app.ui.home.myBooking.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.databinding.ListViewCanclebookingItemBinding
import com.homey.app.ui.home.myBooking.model.MyBookingData


class CancleBookingListAdapter :
    RecyclerView.Adapter<CancleBookingListAdapter.ViewHolder>() {

    private val itemList = mutableListOf<MyBookingData>()
    lateinit var callBack: (String) -> Unit

    private var selectedPosition: Int = 0
    private var lastSelectedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<MyBookingData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewCanclebookingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyBookingData) = with(binding) {
            checkBox.text = item.status
            checkBox.setOnCheckedChangeListener(null)

            if (selectedPosition==layoutPosition){
                callBack(checkBox.text.toString())
            }
            checkBox.isChecked = selectedPosition == layoutPosition

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                lastSelectedPosition = selectedPosition
                selectedPosition = layoutPosition
                notifyItemChanged(lastSelectedPosition, 1)
                notifyItemChanged(selectedPosition, 1)
            }

            itemView.setOnClickListener {
                checkBox.performClick()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewCanclebookingItemBinding.inflate(
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

    fun onClick(kFunction1: (String) -> Unit) {
        this.callBack = kFunction1
    }
}