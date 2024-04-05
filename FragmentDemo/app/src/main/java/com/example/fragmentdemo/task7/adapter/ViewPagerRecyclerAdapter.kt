package com.example.fragmentdemo.task7.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.ViewpagerRecyclerLayoutBinding
import com.example.fragmentdemo.task7.pojo.RecyclerData

class ViewPagerRecyclerAdapter :
    RecyclerView.Adapter<ViewPagerRecyclerAdapter.ViewHolder>() {
    private val itemsList = mutableListOf<RecyclerData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<RecyclerData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ViewpagerRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecyclerData) = with(binding) {
            Glide.with(imageView).load(item.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
            textViewDescription.text = item.description
            textViewTitle.text = item.title
//            constraint.backgroundTintList= ColorStateList.valueOf(Color.parseColor(""))
            constraint.setBackgroundColor(
                textViewTitle.context.resources.getColor(
                    item.color,
                    null
                )
            )

//            constraint.setBackgroundColor(setImage())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewpagerRecyclerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun getList(): MutableList<RecyclerData> = itemsList

    private fun setColor(): Int {
        return arrayOf(
           R.color.teal_700,
           R.color.teal_200,
           R.color.blue,
           R.color.dark_red,
           R.color.purple_500,
           R.color.purple_200,

        ).random()
    }
}