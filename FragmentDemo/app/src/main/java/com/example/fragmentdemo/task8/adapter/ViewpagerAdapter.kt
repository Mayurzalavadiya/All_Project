package com.example.fragmentdemo.task8.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.CustomViewpagerLayoutBinding

class ViewpagerAdapter : RecyclerView.Adapter<ViewpagerAdapter.ViewHolder>() {

    private var list = arrayListOf<Int>()


    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: List<Int>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: CustomViewpagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Int) = with(binding) {
            imageView.setImageResource(getItem())
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

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        list.clear()
    }

    private fun getItem(): Int {
        return arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image9,
            R.drawable.image10,
            ).random()
    }
}
