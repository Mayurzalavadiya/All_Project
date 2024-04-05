package com.example.fragmentdemo.task7.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.IndicatorListViewBinding


//IndicatorAdapter
//itemsList
//IndicatorData
//IndicatorListViewBinding

class IndicatorAdapter :
    RecyclerView.Adapter<IndicatorAdapter.ViewHolder>() {

    private var itemsList: Int? = null
    private var selectPosition: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: Int) {
        Log.e("TAG", "addItem: $items")
        this.itemsList = items
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPosition(position: Int) {
        Log.e("TAG", "setPosition: $position")
        this.selectPosition = position
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: IndicatorListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
            if (item == selectPosition) {
                imageview.setImageResource(R.drawable.bg_selected_indicator)
            } else {
                imageview.setImageResource(R.drawable.bg_un_selected)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IndicatorListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsList!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}