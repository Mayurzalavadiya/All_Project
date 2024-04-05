package com.example.listviewdemo.task6.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listviewdemo.databinding.RecyclrerMultipleItemSelectBinding
import com.example.listviewdemo.task6.pojo.SingleSelectData

class SingleSelectAdapter :
    RecyclerView.Adapter<SingleSelectAdapter.ViewHolder>() {

    private val itemList = mutableListOf<SingleSelectData>()
    private var checkedPosition = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: MutableList<SingleSelectData>) {
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: RecyclrerMultipleItemSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SingleSelectData) = with(binding) {
            Glide.with(imageViewItem).load(item.image).into(imageViewItem)
            textViewTitle.text = item.name
            textViewDes.text = item.des

//            checkBox.isChecked = checkedPosition == adapterPosition
//
//            itemView.setOnClickListener {
//                checkBox.isChecked = true
//                if (checkedPosition != adapterPosition) {
//                    notifyItemChanged(checkedPosition)
//                    checkedPosition = adapterPosition
//                }
//            }

            val isCheck: Boolean = item.isSelected
            checkBox.isChecked = isCheck

            itemView.setOnClickListener {
                isItemSelect(adapterPosition)
                item.isSelected = !item.isSelected
                notifyItemChanged(adapterPosition, Unit)
            }
        }

        fun checkedItem() {
            binding.checkBox.isChecked = false
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty() && payloads[0] == 0) {
            holder.checkedItem()
        } else {
            super.onBindViewHolder(holder, position, payloads)

        }
    }

    private fun isItemSelect(position: Int) {
        val temp = itemList.indexOfFirst { it.isSelected     }
        if (temp >= 0 && temp != position) {
            itemList[temp].isSelected = false
            notifyItemChanged(temp, 0)
        }
    }
}
