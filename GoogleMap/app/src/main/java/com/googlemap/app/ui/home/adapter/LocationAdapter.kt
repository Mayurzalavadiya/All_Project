package com.googlemap.app.ui.home.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.model.LatLng
import com.googlemap.app.R
import com.googlemap.app.databinding.ItemListLatlngBinding
import com.googlemap.app.ui.home.model.LatLngData
import kotlin.reflect.KFunction1


class LocationAdapter :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private val itemList = mutableListOf<LatLngData>()

    lateinit var onClick: (LatLng) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<LatLngData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun updateItem(items: LatLngData) {
        itemList.clear()
        this.itemList.add(items)
        notifyDataSetChanged()
//        notifyItemInserted(itemList.size)
    }

    inner class ViewHolder(val binding: ItemListLatlngBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LatLngData) = with(binding) {
            textviewLat1.text = item.location.latitude.toString()
            textviewLng1.text = item.location.longitude.toString()
            val requestOptions = RequestOptions().transform(CircleCrop())
            Glide.with(itemView.context).load(item.image).diskCacheStrategy(DiskCacheStrategy.ALL).apply(requestOptions).into(imageview)

            if (item.isSelect) {
                constraint.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.black
                    )
                )
            } else {
                constraint.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
         /*   itemView.setOnClickListener {
                if (item.isSelect) {
                    constraint.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.white
                        )
                    )
                    itemList[adapterPosition].isSelect = !item.isSelect
                    onClick(itemList[adapterPosition].location)
                } else {
                    itemList[adapterPosition].isSelect = !item.isSelect
                    onClick(itemList[adapterPosition].location)
                    constraint.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.black
                        )
                    )
                }
            }*/

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListLatlngBinding.inflate(
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

    fun callBack(kFunction1: KFunction1<LatLng, Unit>) {
        this.onClick = kFunction1
    }

    fun getSelectLocation(): MutableList<LatLng> {
        var list: MutableList<LatLng>?= null
        for (i in itemList.indices) {
            if (itemList[i].isSelect) {
                list?.add(itemList[i].location)
            }
        }
        return list!!
    }

    fun selectItem(item: LatLng) {
        for (i in itemList.indices) {
            if (itemList[i].location == item) {
                itemList[i].isSelect = !itemList[i].isSelect
                notifyItemChanged(i)
            }
        }
    }


    fun clearList(){
        itemList.clear()
        notifyDataSetChanged()
    }

}