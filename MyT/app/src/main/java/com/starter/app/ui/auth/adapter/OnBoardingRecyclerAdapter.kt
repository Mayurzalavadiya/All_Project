package com.starter.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starter.app.databinding.OnBoardingViewPagerBinding
import com.starter.app.ui.auth.pojo.OnBoardingData

class OnBoardingRecyclerAdapter :
    RecyclerView.Adapter<OnBoardingRecyclerAdapter.ViewHolder>() {
    private val itemsList = mutableListOf<OnBoardingData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<OnBoardingData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: OnBoardingViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnBoardingData) = with(binding) {
            imageViewMain.setImageResource(item.mainImage)
            textviewTitle.text = item.title
            textviewDes.text = item.description
            imageIndicator.setImageResource(item.slider)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OnBoardingViewPagerBinding.inflate(
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

    fun getList(): MutableList<OnBoardingData> = itemsList

}