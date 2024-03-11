package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.databinding.ListViewAddmoneyItemBinding
import com.homey.app.ui.home.settings.model.AddMoneyData


class AddMoneyAdapter :
    RecyclerView.Adapter<AddMoneyAdapter.ViewHolder>() {

    private val itemList = mutableListOf<AddMoneyData>()

    private var selectedPosition: Int = 0
    private var lastSelectedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<AddMoneyData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun updateItem(item: AddMoneyData) {
        this.itemList.add(item)
        notifyItemInserted(itemList.size)
    }

    inner class ViewHolder(val binding: ListViewAddmoneyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddMoneyData) = with(binding) {
            val cardNumber =  item.cardNumber.substring(item.cardNumber.length - 3, item.cardNumber.length) ;
            textviewCardNumber.text = cardNumber
            checkBox.setOnCheckedChangeListener(null)

            checkBox.isChecked = selectedPosition == layoutPosition

            checkBox.setOnCheckedChangeListener { _, _ ->
                lastSelectedPosition = selectedPosition
                selectedPosition = layoutPosition
                notifyItemChanged(lastSelectedPosition, 1)
                notifyItemChanged(selectedPosition, 1)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewAddmoneyItemBinding.inflate(
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