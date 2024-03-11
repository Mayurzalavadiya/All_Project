package com.example.listviewdemo.task5.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listviewdemo.databinding.RecyclrerSelectedItemBinding
import com.example.listviewdemo.task5.pojo.MultiSelectData

class ItemSelectAdapter :
    RecyclerView.Adapter<ItemSelectAdapter.ViewHolder>() {

    val list = arrayListOf<MultiSelectData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: ArrayList<MultiSelectData>?) {
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: RecyclrerSelectedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MultiSelectData) = with(binding) {
            Glide.with(imageViewItem).load(item.image).into(imageViewItem)
            textViewTitle.text = item.name
            textViewDes.text = item.des

            itemView.setOnClickListener {
                list.removeAt(adapterPosition)
                notifyItemChanged(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclrerSelectedItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}