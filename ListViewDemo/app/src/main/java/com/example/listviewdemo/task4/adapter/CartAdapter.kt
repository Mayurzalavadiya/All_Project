package com.example.listviewdemo.task4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.RecyclerCartItemBinding
import com.example.listviewdemo.task4.pojo.ProductItemData

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    val list = arrayListOf<ProductItemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: ArrayList<ProductItemData>?) {
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductItemData) = with(binding) {
            Glide.with(imageViewProduct)
                .load(item.image)
                .placeholder(R.drawable.product)
                .into(imageViewProduct)

            imageViewRemove.setOnClickListener {
                list.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerCartItemBinding.inflate(
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
}