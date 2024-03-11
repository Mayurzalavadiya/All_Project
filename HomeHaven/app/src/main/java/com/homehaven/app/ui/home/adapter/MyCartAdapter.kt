package com.rapido.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homehaven.app.databinding.MycartListViewBinding
import com.homehaven.app.ui.home.model.MyCartTData


//MyCartAdapter
//itemsList
//MyCartTData
//MycartListViewBinding

class MyCartAdapter :
    RecyclerView.Adapter<MyCartAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<MyCartTData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<MyCartTData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MycartListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyCartTData) = with(binding) {
            imageViewMain.setImageResource(item.image)
            textviewColor.text = item.productColor
            textviewName.text = item.productName
            textviewOldPrice.text = item.productOldPrice
            textviewPrice.text = item.productPrice
            textviewNumber.text = item.productCount.toString()

            var itemCout = item.productCount
            Log.e("TAG", "bind: $itemCout")
            imageViewMinus.setOnClickListener {
                if (itemCout > 1) {
                    textviewNumber.text = itemCout--.toString()
                    itemsList[adapterPosition].productCount = itemCout--
                    notifyItemChanged(adapterPosition, 1)
                }
            }
            imageViewPlus.setOnClickListener {
                textviewNumber.text = itemCout++.toString()
                itemsList[adapterPosition].productCount = itemCout++
                notifyItemChanged(adapterPosition, 1)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MycartListViewBinding.inflate(
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