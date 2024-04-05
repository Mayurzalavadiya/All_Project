package com.starter.app.ui.setGoal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.starter.app.data.pojo.request.UpdateReadingGoalRequest
import com.starter.app.data.pojo.response.Reading
import com.starter.app.data.pojo.response.ReadingResponse
import com.starter.app.databinding.ReadingListRecyclerViewBinding

class ReadingListAdapter :
    RecyclerView.Adapter<ReadingListAdapter.ViewHolder>() {

    private val readingList = mutableListOf<Reading>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(items: ReadingResponse) {
        this.readingList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ReadingListRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var id: Int? = null
        fun bind(item: Reading) = with(binding) {

            Glide.with(imageview).load(item.imageUrl).diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(imageview)
//            item.backgroundColor?.let {
//                imageview.setBackgroundColor(Color.parseColor(item.backgroundColor))
//            }


            textviewTitleReading.text = item.readingName
        }

        override fun onClick(p0: View?) {
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ReadingListRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = readingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(readingList[position])
    }

    fun getReadingList(): MutableList<UpdateReadingGoalRequest.Reading> {
        val list = mutableListOf<UpdateReadingGoalRequest.Reading>()
        readingList.forEach { it ->
            list.add(
                UpdateReadingGoalRequest.Reading(
                    it.mandatory,
                    it.readingsMasterId

                )
            )
        }
        return list
    }
}