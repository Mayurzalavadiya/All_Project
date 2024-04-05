package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.ListViewFaqItemBinding
import com.homey.app.ui.home.settings.model.FAQData


class FAQAdapter(val itemList : MutableList<FAQData>) :
    RecyclerView.Adapter<FAQAdapter.ViewHolder>() {



    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(items: List<FAQData>) {
        itemList.clear()
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewFaqItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FAQData) = with(binding) {
            textviewQuestion.text = "${layoutPosition + 1} ${item.question}"

            if (item.isExpandable) {
                textviewAnswer.visibility =
                    View.VISIBLE
                imageviewArrow.setImageResource(R.drawable.up_arrow_icon)
            } else {
                textviewAnswer.visibility = View.GONE
                imageviewArrow.setImageResource(R.drawable.down_arrow_icon)
            }

            itemView.setOnClickListener {
                isAnyItemExpanded(layoutPosition)
                item.isExpandable = !item.isExpandable
                notifyItemChanged(layoutPosition, 1)
            }


        }
    }

    fun isAnyItemExpanded(position: Int) {
        val temp = itemList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            itemList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewFaqItemBinding.inflate(
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

}