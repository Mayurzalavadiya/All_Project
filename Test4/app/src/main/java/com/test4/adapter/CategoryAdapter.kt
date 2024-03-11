package com.test4.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test4.pojo.CategoryItem
import com.test4.databinding.ItemListTitleBinding
import com.test4.`interface`.RecyclerClickListener

class CategoryAdapter(val clickItem: RecyclerClickListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var list = mutableListOf<CategoryItem>()
    private var selectedPosition: Int = 0
    private var lastSelectedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<CategoryItem>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemListTitleBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: CategoryItem) = with(binding) {
            textViewName.text = item.name

            textViewName.setOnClickListener(this@ViewHolder)

            if (selectedPosition == adapterPosition) {
                textViewName.setTextColor(Color.parseColor("#FF000000"))
            } else {
                textViewName.setTextColor(Color.parseColor("#A6A6A6"))

            }
        }

        override fun onClick(v: View?) {
            lastSelectedPosition = selectedPosition
            selectedPosition = adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            clickItem.onClick(adapterPosition, v)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemListTitleBinding.inflate(
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
