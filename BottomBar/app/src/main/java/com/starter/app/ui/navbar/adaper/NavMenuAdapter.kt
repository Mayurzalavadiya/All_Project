package com.starter.app.ui.navbar.adaper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starter.app.data.pojo.response.State
import com.starter.app.data.pojo.response.StateListResponse
import com.starter.app.databinding.NavListViewBinding
import com.starter.app.databinding.StateListViewBinding
import com.starter.app.ui.navbar.pojo.NavMenuData

class NavMenuAdapter : RecyclerView.Adapter<NavMenuAdapter.ViewHolder>() {

    private val list = mutableListOf<NavMenuData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(navMenuItem: MutableList<NavMenuData>) {
        this.list.addAll(navMenuItem)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: NavListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NavMenuData) {
            with(binding) {
                item.image?.let { imageviewIcon.setImageResource(it) }
                textViewName.text = item.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NavListViewBinding.inflate(
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


}