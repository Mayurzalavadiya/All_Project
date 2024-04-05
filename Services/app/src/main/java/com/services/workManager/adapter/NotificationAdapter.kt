package com.services.workManager.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.services.databinding.ItemViewNotificationBinding
import com.services.workManager.User


class NotificationAdapter :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private val itemList = mutableListOf<User>()
    lateinit var isClick: (Int) -> Unit


    private var selectedPosition: Int = 0
    private var lastSelectedPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: List<User>) {
        itemList.clear()
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemViewNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) = with(binding) {

            textviewId1.text = item.id.toString()
            textviewName1.text = item.type
            textviewTime.text = item.time

            imageviewDelete.setOnClickListener {
                isClick(adapterPosition + 1)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }


    fun callback(kFunction1: (Int) -> Unit) {
        isClick = kFunction1
    }
}