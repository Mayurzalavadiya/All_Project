package com.example.fragmentdemo.task5.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.CustomViewpagerLayoutBinding

class ViewPager2Adapter : RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {

    private val items = mutableListOf<Int>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: MutableList<Int>) {
        this.items.addAll(item)
        notifyDataSetChanged()

    }

    inner class ViewHolder(val binding: CustomViewpagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Int) = with(binding) {
            Glide.with(imageView).load(image).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CustomViewpagerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun setImage():Int{
        return arrayOf(R.drawable.image5, R.drawable.image4, R.drawable.image7, R.drawable.image6).random()
    }
}