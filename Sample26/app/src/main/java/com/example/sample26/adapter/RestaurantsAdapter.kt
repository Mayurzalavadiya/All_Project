package com.example.sample26.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample26.activity.CategoryActivity
import com.example.sample26.Const
import com.example.sample26.model.RestaurantsModal
import com.example.sample26.R

class RestaurantsAdapter(list: ArrayList<RestaurantsModal>,context: Context) : RecyclerView.Adapter<RestaurantsAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_restarants,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
//		holder.itemName.text = restaurantsList[position].itemName
//		holder.itemImage.setImageResource(restaurantsList[position].itemImg)

        holder.itemView.setOnClickListener {
            Const.moveToNextScreen(holder.itemView.context, CategoryActivity::class.java)
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val itemName: AppCompatTextView = itemView.findViewById(R.id.textViewItemName)
//		val itemImage: AppCompatImageView = itemView.findViewById(R.id.ImageViewItem)
    }
}
