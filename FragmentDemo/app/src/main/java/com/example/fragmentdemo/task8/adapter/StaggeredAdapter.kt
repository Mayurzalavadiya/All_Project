package com.example.fragmentdemo.task8.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentdemo.databinding.RecyclerListStaggeredBinding

class StaggeredAdapter(val item :ArrayList<Int>) : RecyclerView.Adapter<StaggeredAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerListStaggeredBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
            imageView.setImageResource(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("TAG", "onCreateViewHolder: ")
        return ViewHolder(
            RecyclerListStaggeredBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
        Log.i("TAG", "onBindViewHolder: $position")
    }

    override fun getItemCount(): Int {
        return item.size
    }

}
