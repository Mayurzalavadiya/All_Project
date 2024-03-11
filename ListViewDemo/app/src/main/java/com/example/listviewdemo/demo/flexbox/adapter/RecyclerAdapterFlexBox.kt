package com.example.listviewdemo.demo.flexbox.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.pojo.RecyclerFlexItemData
import com.example.listviewdemo.databinding.RecyclerListViewFlexBoxBinding

class RecyclerAdapterFlexBox() : RecyclerView.Adapter<RecyclerAdapterFlexBox.ViewHolder>() {

    private var list = mutableListOf<RecyclerFlexItemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<RecyclerFlexItemData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerListViewFlexBoxBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: RecyclerFlexItemData) = with(binding) {
            Log.i("TAG", "bind: ")
            textView.text = item.name

            textView.setOnClickListener{

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("TAG", "onCreateViewHolder: ")
        return ViewHolder(
            RecyclerListViewFlexBoxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        Log.i("TAG", "onBindViewHolder: $position")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.i("TAG", "onAttachedToRecyclerView:")
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        Log.i("TAG", "onDetachedFromRecyclerView: ")
    }
}
