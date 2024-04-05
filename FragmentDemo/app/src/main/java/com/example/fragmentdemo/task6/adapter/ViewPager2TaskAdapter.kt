package com.example.fragmentdemo.task6.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fragmentdemo.databinding.CustomViewpagerLayoutBinding

class ViewPager2TaskAdapter(private val items: ArrayList<Int>) :
    RecyclerView.Adapter<ViewPager2TaskAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: CustomViewpagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
//            textView.text = item
            Glide.with(imageView).load(item).diskCacheStrategy(DiskCacheStrategy.ALL)
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
}