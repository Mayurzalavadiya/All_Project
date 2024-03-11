package com.homehaven.app.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homehaven.app.databinding.BannerListViewBinding


//BannerAdapter
//itemsList
//Int
//BannerListViewBinding

class BannerAdapter :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<Int>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<Int>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: BannerListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
            imageViewMain.setImageResource(item)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BannerListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

}