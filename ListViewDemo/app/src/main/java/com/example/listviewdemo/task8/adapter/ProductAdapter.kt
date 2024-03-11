package com.example.listviewdemo.task8.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.ProductListItemBinding
import com.example.listviewdemo.task8.interfaces.ClickListener
import com.example.listviewdemo.task8.pojo.ProductBillData

class ProductAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val itemList = mutableListOf<ProductBillData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: ProductBillData) {
        var index: Int? = null
        for (i in itemList.indices) {
            if (itemList[i].name?.lowercase() == list.name?.lowercase()) {
                index = i
            }
        }

        if (index != null) {
            itemList[index].price = list.price
            itemList[index].isSelected = false
            notifyItemChanged(index)
        } else {
            this.itemList.add(list)
            notifyDataSetChanged()
        }
//        val itemIndex = itemList.indexOfFirst { it.name?.lowercase() == list.name?.lowercase() }
//            if (!itemIndex.equals("")) {
//            itemList[itemIndex].price = list.price
//            itemList[itemIndex].isSelected = false
//            notifyItemChanged(itemIndex)
//
//        } else {
//            this.itemList.add(list)
//            notifyDataSetChanged()
//        }
    }

    inner class ViewHolder(val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        @SuppressLint("SetTextI18n", "StringFormatMatches")
        fun bind(item: ProductBillData) = with(binding) {
            textViewTitle.text =
                "${item.name}\n${itemView.context.getString(R.string.rs, item.price)}"
            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = item.isSelected

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                checkBox.isChecked = !item.isSelected
                itemList[adapterPosition].isSelected = !item.isSelected
                onClick(checkBox)
            }
            itemView.setOnClickListener {
                checkBox.performClick()

            }
        }

        override fun onClick(v: View?) {
            clickListener.onClick(adapterPosition, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun getSelectedList(position: Int): ProductBillData = itemList[position]

    fun removeCheck(id: Int?) {
        for (i in itemList.indices) {
            if (itemList[i].id == id) {
                itemList[i].isSelected = false
                notifyItemChanged(i)
            }
        }
    }
}