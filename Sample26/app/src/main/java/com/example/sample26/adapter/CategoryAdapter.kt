package com.example.sample26.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sample26.model.CategoryModal
import com.example.sample26.R


class CategoryAdapter(
    private val categoryList: ArrayList<CategoryModal>,
    private val context: Context,
    private var selectedPosition: Int = -1,
    private var lastSelectedPosition: Int = -1
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_category,
            parent, false
        )
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
//        holder.itemName.text = categoryList[position].itemName
//        holder.itemIcon.setImageResource(categoryList[position].itemImg)

        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == position) {
            holder.itemImage.setImageResource(R.drawable.ellipse_15)
            holder.itemIcon.setColorFilter(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.itemImage.setImageResource(R.drawable.ellipse_14)
            holder.itemIcon.setColorFilter(ContextCompat.getColor(context, R.color.lightGreen))
        }

    }

    override fun getItemCount(): Int {
        return 6
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val itemName: AppCompatTextView = itemView.findViewById(R.id.textViewItemName)
        val itemImage: AppCompatImageView = itemView.findViewById(R.id.ImageViewItem)
        val itemIcon: AppCompatImageView = itemView.findViewById(R.id.ImageViewIcon)
    }
}
