package com.example.listviewdemo.demo.list.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.demo.interfaces.RecyclerClickItem
import com.example.listviewdemo.pojo.RecyclerItemData
import com.example.listviewdemo.databinding.RecyclerListViewHorizontalBinding

class RecyclerAdapterHorizontal(val recyclerClickItem: RecyclerClickItem) :
    RecyclerView.Adapter<RecyclerAdapterHorizontal.ViewHolder>() {

    private var list = mutableListOf<RecyclerItemData>()
    private var selectedPosition: Int = 0
    private var lastSelectedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<RecyclerItemData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerListViewHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: RecyclerItemData) = with(binding) {
            Log.i("TAG", "bind: ")
            textView.text = item.name
            imageView.setImageResource(item.image)

            constraint.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = layoutPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
            if (selectedPosition == layoutPosition) {
                constraint.setBackgroundColor(Color.parseColor("#1E70C6"))
            } else {
                constraint.setBackgroundColor(Color.parseColor("#ffffff"))
            }
            imageView.setOnClickListener(this@ViewHolder)
            textView.setOnClickListener(this@ViewHolder)
        }


        override fun onClick(v: View?) {
            recyclerClickItem.onClick(list[layoutPosition], v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("TAG", "onCreateViewHolder: ")
        return ViewHolder(
            RecyclerListViewHorizontalBinding.inflate(
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
