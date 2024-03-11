package com.example.listviewdemo.task4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.RecyclerProductItemBinding
import com.example.listviewdemo.task4.interfaces.ProductItemClick
import com.example.listviewdemo.task4.pojo.ProductItemData

class ProductAdapter(val productClick: ProductItemClick) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    val list = mutableListOf<ProductItemData>()
    var itemListPosition = mutableSetOf<Int>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(list: MutableList<ProductItemData>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RecyclerProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: ProductItemData) = with(binding) {
            Glide.with(imageViewProduct)
                .load(item.image)
                .placeholder(R.drawable.product)
                .into(imageViewProduct)

            imageViewProduct.setOnClickListener(this@ViewHolder)

        }

        override fun onClick(v: View?) {

            productClick.onClick(adapterPosition, v)
            itemListPosition.add(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerProductItemBinding.inflate(
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