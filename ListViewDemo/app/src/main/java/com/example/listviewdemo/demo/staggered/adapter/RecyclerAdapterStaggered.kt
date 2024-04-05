package com.example.listviewdemo.demo.staggered.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.demo.interfaces.RecyclerClickItem
import com.example.listviewdemo.pojo.RecyclerItemData
import com.example.listviewdemo.databinding.RecyclerListViewStaggeredBinding

class RecyclerAdapterStaggered(val recyclerClickItem: RecyclerClickItem) : RecyclerView.Adapter<RecyclerAdapterStaggered.ViewHolder>() {

    private var list = mutableListOf<RecyclerItemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<RecyclerItemData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerListViewStaggeredBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {

        fun bind(item: RecyclerItemData) = with(binding) {
            Log.i("TAG", "bind: ")
            textView.text = item.name
            imageView.setImageResource(item.image)

            textView.setOnClickListener(this@ViewHolder)
        }


        override fun onClick(v: View?) {
            recyclerClickItem.onClick(list[layoutPosition],v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("TAG", "onCreateViewHolder: ")
        return ViewHolder(
            RecyclerListViewStaggeredBinding.inflate(
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
