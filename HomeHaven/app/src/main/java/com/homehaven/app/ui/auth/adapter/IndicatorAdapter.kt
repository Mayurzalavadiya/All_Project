package com.homehaven.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homehaven.app.R
import com.homehaven.app.databinding.IndicatorLayoutBinding


//IndicatorAdapter
//TotalItems
//Int
//IndicatorLayoutBinding

class IndicatorAdapter : RecyclerView.Adapter<IndicatorAdapter.ViewHolder>() {

    private var TotalItems: Int = 0
    private var selectPosition: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: Int) {
        this.TotalItems = items
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPosition(position: Int) {
        this.selectPosition = position
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: IndicatorLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
            imageview.setImageResource(
                if (item == selectPosition) R.drawable.selected_progress_bar_circle
                else R.drawable.unselected_progress_bar_circle
            )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IndicatorLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = TotalItems

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}