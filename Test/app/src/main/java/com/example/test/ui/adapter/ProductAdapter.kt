package com.example.test.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.data.pojo.MyProducts
import com.example.test.databinding.ProductListViewBinding

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var productList = mutableListOf<MyProducts>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(itemList: MutableList<MyProducts>) {
        this.productList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        fun bind(item: MyProducts) = with(binding) {
            textViewName.text = item.title
            textViewLocation.text = item.date
            textViewPrice.text = item.price
        }

        override fun onClick(v: View?) {
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

    fun clear() {
        productList.clear()
    }

}