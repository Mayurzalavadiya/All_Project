package com.example.sample26.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample26.Const
import com.example.sample26.activity.RestaurantDetailsActivity
import com.example.sample26.model.CategoriesModal
import com.example.sample26.R

class CategoriesAdapter(
    private val categoriesList: ArrayList<CategoriesModal>,
    private val context: Context,
) : RecyclerView.Adapter<CategoriesAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_burger,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
//		holder.courseNameTV.text = categoriesList.get(position).courseName
//		holder.courseIV.setImageResource(categoriesList.get(position).courseImg)

        holder.itemView.setOnClickListener {
            Const.moveToNextScreen(this.context, RestaurantDetailsActivity::class.java)
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val courseNameTV: AppCompatTextView = itemView.findViewById(R.id.textViewTitle)
//		val courseIV: ImageView = itemView.findViewById(R.id.ImageViewItem)
    }
}
