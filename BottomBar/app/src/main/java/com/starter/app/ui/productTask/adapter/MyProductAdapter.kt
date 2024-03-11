package com.starter.app.ui.productTask.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.starter.app.databinding.ProductListViewBinding
import com.starter.app.ui.productTask.interfaces.ClickListener
import com.starter.app.ui.productTask.pojo.MyProduct

class MyProductAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<MyProductAdapter.ViewHolder>() {
    private var productList = mutableListOf<MyProduct>()


    @SuppressLint("NotifyDataSetChanged")
    fun addItem(itemList: MyProduct?) {
        if (itemList != null) {
            this.productList.add(itemList)
        }
        notifyDataSetChanged()
    }

    fun updateItem(item: MyProduct?) {
        if (item != null) {
            item.position?.let {
                productList.removeAt(item.position.toInt())
                productList.add(it.toInt(), item)
                notifyItemChanged(item.position.toInt())
            }
        }
    }

    inner class ViewHolder(private val binding: ProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        @SuppressLint("SetTextI18n")
        fun bind(item: MyProduct) = with(binding) {
            Glide.with(imageView).load(item.image).into(imageView)
//            imageView.setImageResource(item.image)
            textViewTitle.text = item.courseName
            textViewDes.text = item.description
            textViewPrice1.text = item.price

            //start date
            textViewDate.text = item.startDate?.split(" ")?.get(0)
            textViewMonYear.text = "${
                item.startDate?.split(" ")?.get(1)?.split(",")?.get(0)
            }\n${item.startDate?.split(" ")?.get(2)}"

            //end date
            textViewEndDate.text = item.endDate?.split(" ")?.get(0)
            textViewEndMonYear.text = "${
                item.endDate?.split(" ")?.get(1)?.split(",")?.get(0)
            }\n${item.endDate?.split(" ")?.get(2)}"

            setClickListener()
        }

        private fun setClickListener() {
            binding.imageViewEdit.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onClick(adapterPosition, v)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductListViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }


    fun getItemList(position: Int): MyProduct {
        return productList[position]
    }
}