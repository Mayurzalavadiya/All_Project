package com.test4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test4.databinding.ItemListViewBinding
import com.test4.pojo.MenuItem

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

     private var list = mutableListOf<MenuItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<MenuItem>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem) = with(binding) {
            ImageViewImage.setImageResource(item.image)
            textViewItemName.text = item.title
            textViewItemDescription.text = item.description
            textViewPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        list.clear()
    }
}