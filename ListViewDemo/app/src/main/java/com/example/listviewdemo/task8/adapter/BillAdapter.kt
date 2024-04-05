package com.example.listviewdemo.task8.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.databinding.BillListItemBinding
import com.example.listviewdemo.task8.interfaces.ClickListener
import com.example.listviewdemo.task8.pojo.ProductBillData

class BillAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    private val itemList = mutableListOf<ProductBillData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: ProductBillData) {
        this.itemList.add(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: List<ProductBillData>) {
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(id: Int?) {
        itemList.removeIf { it.id == id }
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: BillListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        @SuppressLint("SetTextI18n")
        fun bind(item: ProductBillData) = with(binding) {
            textViewTitle.text = "${item.name} ${item.price}"

            imageViewClose.setOnClickListener {
                onClick(imageViewClose)
            }
        }

        override fun onClick(v: View?) {
            clickListener.onClick(adapterPosition, v)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BillListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }


    fun getList(): MutableList<ProductBillData> = itemList

}