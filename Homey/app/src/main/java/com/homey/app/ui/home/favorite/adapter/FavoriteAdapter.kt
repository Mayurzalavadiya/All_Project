package com.homey.app.ui.home.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.databinding.ListViewFavoriteItemBinding
import com.homey.app.ui.home.favorite.model.FavoriteData


class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val favoriteList = mutableListOf<FavoriteData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<FavoriteData>) {
        this.favoriteList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewFavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteData) = with(binding) {

            imageviewHotel.setImageResource(item.image)
            textviewHotel.text = item.hotelName
            textviewPrice.text ="\$${item.price}"
            imageviewFavorite.setOnClickListener {
                favoriteList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewFavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

}