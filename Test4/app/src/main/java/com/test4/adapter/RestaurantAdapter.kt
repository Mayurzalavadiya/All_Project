package com.test4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test4.databinding.ItemListCusinesBinding
import com.test4.databinding.ItemListRestaurantsBinding
import com.test4.databinding.ItemListTradingRestaurantsBinding

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {


    private var list = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<String>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemListRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item : String) = with(binding){
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListRestaurantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       holder.bind(list[position])
    }
}