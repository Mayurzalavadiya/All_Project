package com.rapido.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homehaven.app.databinding.MyaccountListViewBinding
import com.homehaven.app.ui.home.model.MyAccountData


//MyAccountAdapter
//itemsList
//MyAccountData
//MyaccountListViewBinding

class MyAccountAdapter :
    RecyclerView.Adapter<MyAccountAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<MyAccountData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<MyAccountData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MyaccountListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyAccountData) = with(binding) {
            imageViewIcon.setImageResource(item.image)
            textviewName.text = item.name

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MyaccountListViewBinding.inflate(
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