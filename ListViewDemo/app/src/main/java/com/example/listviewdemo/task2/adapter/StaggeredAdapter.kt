package com.example.listviewdemo.task2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.databinding.RecyclerListStaggeredBinding
import com.example.listviewdemo.task1.interfaces.RecyclerGridClickItem
import com.example.listviewdemo.pojo.ItemData

class StaggeredAdapter(val recyclerClickItem: RecyclerGridClickItem) :
    RecyclerView.Adapter<StaggeredAdapter.ViewHolder>() {

    private var list = mutableListOf<ItemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<ItemData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    fun addItemNew(items: MutableList<ItemData>) {
        this.list.addAll(items)
    }

    inner class ViewHolder(private val binding: RecyclerListStaggeredBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: ItemData) = with(binding) {
            imageView.setImageResource(item.image)

            imageView.setOnClickListener(this@ViewHolder)

        }


        override fun onClick(v: View?) {
            recyclerClickItem.onClick(list[adapterPosition], v, adapterPosition + 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        Log.i("TAG", "onCreateViewHolder: ")
        return ViewHolder(
            RecyclerListStaggeredBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
//        Log.i("TAG", "onBindViewHolder: $position")
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
