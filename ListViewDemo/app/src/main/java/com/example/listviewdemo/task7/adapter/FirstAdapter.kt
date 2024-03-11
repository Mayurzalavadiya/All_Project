package com.example.listviewdemo.task7.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.databinding.TestListItemBinding
import com.example.listviewdemo.task7.pojo.FirstRecyclerData

class FirstAdapter : RecyclerView.Adapter<FirstAdapter.ViewHolder>() {

    private val itemList = mutableListOf<FirstRecyclerData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: FirstRecyclerData) {
        this.itemList.add(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: List<FirstRecyclerData>) {
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeSelectedItem() {
        itemList.removeIf { it.isSelected }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: TestListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FirstRecyclerData) = with(binding) {
            textViewTitle.text = item.name
            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = item.isSelected

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                checkBox.isChecked = !item.isSelected
                itemList[adapterPosition].isSelected = !item.isSelected
            }

            itemView.setOnClickListener {
                checkBox.performClick()
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TestListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun clearList() {
        itemList.clear()
    }

    fun getSelectedList(): List<FirstRecyclerData> = itemList.filter { it.isSelected }
}