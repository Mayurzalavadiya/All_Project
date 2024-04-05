package com.example.listviewdemo.task5.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listviewdemo.databinding.RecyclrerMultipleItemSelectBinding
import com.example.listviewdemo.task5.listeners.ClickListener
import com.example.listviewdemo.task5.pojo.MultiSelectData

class MultiSelectAdapter(val clickEvent: ClickListener) :
    RecyclerView.Adapter<MultiSelectAdapter.ViewHolder>() {

    private val itemList = mutableListOf<MultiSelectData>()
    private val idList = arrayListOf<Int>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: MutableList<MultiSelectData>) {
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: RecyclrerMultipleItemSelectBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: MultiSelectData) = with(binding) {
            Glide.with(imageViewItem).load(item.image).into(imageViewItem)
            textViewTitle.text = item.name
            textViewDes.text = item.des
            checkBox.isChecked = item.isSelected

            itemView.setOnClickListener {
                if (idList.size < 3 || itemList[adapterPosition].isSelected) {
                    checkBox.isChecked = !item.isSelected
                    itemList[adapterPosition].isSelected = !item.isSelected
                }

                updateSelectedItems()

                onClick(itemView)
            }
        }

        private fun updateSelectedItems() {
            idList.clear()
            for (i in 0 until itemList.size) {
                if (itemList[i].isSelected) {
                    idList.add(i)
                }
            }
        }

        override fun onClick(p0: View?) {
            clickEvent.onClick(idList, p0)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclrerMultipleItemSelectBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

}