package com.rapido.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rapido.app.ui.auth.model.InterestData
import com.rapido.app.R
import com.rapido.app.databinding.InterestListViewBinding

class InterestRecyclerAdapter :
    RecyclerView.Adapter<InterestRecyclerAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<InterestData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<InterestData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: InterestListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InterestData) = with(binding) {

            textviewName.text = item.name

            if (item.isSelect) {
                textviewName.setBackgroundResource(R.drawable.bg_button)
                textviewName.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }

            itemView.setOnClickListener {
                if (!item.isSelect) {
                    textviewName.setBackgroundResource(R.drawable.bg_button)
                    textviewName.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.white
                        )
                    )
                    itemsList[adapterPosition].isSelect = !item.isSelect
                } else {
                    textviewName.setBackgroundResource(R.drawable.bg_text_interest_list)
                    itemsList[adapterPosition].isSelect = !item.isSelect
                    textviewName.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.Black
                        )
                    )
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            InterestListViewBinding.inflate(
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

    fun getSelectList(): String {
        return itemsList.filter { it.isSelect }.joinToString(", ") {
            it.name
        }
    }
}