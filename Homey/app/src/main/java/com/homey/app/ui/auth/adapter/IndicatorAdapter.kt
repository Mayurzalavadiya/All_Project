package com.homey.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.IndicatorLayoutBinding

class IndicatorAdapter :
    RecyclerView.Adapter<IndicatorAdapter.ViewHolder>() {

    private var itemsList: Int =  0
    private var selectPosition: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: Int) {
        this.itemsList = items
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
            if (item == selectPosition) {
                imageview.setImageResource(R.drawable.selected_indicator_circle)
            } else {
                imageview.setImageResource(R.drawable.unselected_indicator_circle)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IndicatorLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsList

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}