package com.social.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.social.app.databinding.IndicatorLayoutBinding
import com.social.app.R

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
                imageview.setImageResource(R.drawable.ic_indicator_selected)
            } else {
                imageview.setImageResource(R.drawable.ic_indicator_unselected)
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

    override fun getItemCount(): Int = itemsList!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}