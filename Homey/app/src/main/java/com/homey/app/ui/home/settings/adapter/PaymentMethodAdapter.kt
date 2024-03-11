package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.databinding.ListViewAddmoneyItemBinding
import com.homey.app.databinding.ListViewPaymentmethodItemBinding
import com.homey.app.ui.home.settings.model.AddMoneyData


class PaymentMethodAdapter :
    RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder>() {

    private val itemList = mutableListOf<AddMoneyData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<AddMoneyData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun updateItem(item: AddMoneyData) {
        this.itemList.add(item)
       notifyItemInserted(itemList.size)
    }

    inner class ViewHolder(val binding: ListViewPaymentmethodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddMoneyData) = with(binding) {
           val cardNumber =  item.cardNumber.substring(item.cardNumber.length - 3, item.cardNumber.length) ;
            textviewCardNumber.text = cardNumber
            imageviewDelete.setOnClickListener {
                itemList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewPaymentmethodItemBinding.inflate(
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