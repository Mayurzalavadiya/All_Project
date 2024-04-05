package com.example.listviewdemo.task3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.databinding.RecyclerListViewGridBinding
import com.example.listviewdemo.pojo.ItemData

class GridAdapter : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private var list = mutableListOf<ItemData>()
    private var selectedPosition: Int = 0
    private var lastSelectedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<ItemData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    fun addItemNew(items: MutableList<ItemData>) {
        this.list.addAll(items)
    }

    inner class ViewHolder(private val binding: RecyclerListViewGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemData) = with(binding) {
            imageView.setImageResource(item.image)

            imageView.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = layoutPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }

            checkBox.isChecked = selectedPosition == layoutPosition

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        Log.i("TAG", "onCreateViewHolder: ")
        return ViewHolder(
            RecyclerListViewGridBinding.inflate(
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

}
