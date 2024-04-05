package com.example.listviewdemo.demo.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.demo.interfaces.RecyclerClickItem
import com.example.listviewdemo.pojo.RecyclerItemData
import com.example.listviewdemo.databinding.RecyclerListViewVerticalBinding

class RecyclerAdapterVertical(val recyclerClickItem: RecyclerClickItem) :
    RecyclerView.Adapter<RecyclerAdapterVertical.ViewHolder>() {

    private var list = mutableListOf<RecyclerItemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<RecyclerItemData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerListViewVerticalBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: RecyclerItemData) = with(binding) {
            textView.text = item.name
            ImageView.setImageResource(item.image)
            textView.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(v: View?) {
            recyclerClickItem.onClick(list[layoutPosition], v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerListViewVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        list.clear()
        notifyDataSetChanged()
    }
}
