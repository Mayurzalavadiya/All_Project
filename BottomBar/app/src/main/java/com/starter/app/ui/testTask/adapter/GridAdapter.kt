package com.starter.app.ui.testTask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starter.app.databinding.RecyclerImageListGridBinding
import com.starter.app.ui.testTask.interfaces.ClickListener

class GridAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private var list = mutableListOf<Int>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<Int>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerImageListGridBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: Int) = with(binding) {
            imageView.setImageResource(item)
            imageView.setOnClickListener {
            }
            imageView.setOnClickListener {
                onClick(imageView)
            }
        }

        override fun onClick(v: View?) {
            clickListener.onClick(adapterPosition, v)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerImageListGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }

}
