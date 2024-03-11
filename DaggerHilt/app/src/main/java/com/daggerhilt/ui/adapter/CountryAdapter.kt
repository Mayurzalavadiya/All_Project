package com.daggerhilt.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daggerhilt.databinding.CountryListViewBinding
import com.daggerhilt.data.response.CountryItem
import com.restapi.Const

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private val itemList = mutableListOf<CountryItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(countryItem: MutableList<CountryItem>) {
        this.itemList.addAll(countryItem)
        notifyDataSetChanged()

    }

    inner class ViewHolder(private val binding: CountryListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryItem) = with(binding) {
            textViewCountry.text = item.country
            textViewName.text = item.name
            textViewAlphaCode.text = item.alphaTwoCode
            textViewDomains.text = item.domains?.get(0)
            textViewWebPages.text = item.webPages?.get(0)

            setClickListener()
        }

        private fun setClickListener() {
            itemView.setOnClickListener {
                Const.showMessage(itemView.context, adapterPosition.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CountryListViewBinding.inflate(
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

    fun clear() {
        itemList.clear()
    }

}