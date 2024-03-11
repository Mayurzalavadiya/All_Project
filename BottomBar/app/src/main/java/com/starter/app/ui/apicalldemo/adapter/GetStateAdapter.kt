package com.starter.app.ui.apicalldemo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starter.app.data.pojo.response.State
import com.starter.app.data.pojo.response.StateListResponse
import com.starter.app.databinding.StateListViewBinding

class GetStateAdapter : RecyclerView.Adapter<GetStateAdapter.ViewHolder>() {

    private val list = mutableListOf<State>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(stateItem: StateListResponse?) {
        if (stateItem != null) {
            this.list.addAll(stateItem)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: StateListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: State) {
            with(binding) {
                textViewStateId.text =  item.id.toString()
                textViewStateName.text = item.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            StateListViewBinding.inflate(
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