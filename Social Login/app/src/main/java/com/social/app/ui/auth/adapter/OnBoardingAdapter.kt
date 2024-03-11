package com.social.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.social.app.databinding.OnBoardingViewPagerBinding
import com.social.app.ui.auth.model.OnBoardingData

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<OnBoardingData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<OnBoardingData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: OnBoardingViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnBoardingData) = with(binding) {
            textviewTitle.text = item.title
            textviewDescription.text = item.des
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

}