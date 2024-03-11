package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.databinding.ListViewBlogItemBinding
import kotlin.reflect.KFunction1


class BlogListAdapter :
    RecyclerView.Adapter<BlogListAdapter.ViewHolder>() {

    private val itemList = mutableListOf<Int>()
    lateinit var callBack: (Boolean) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: List<Int>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewBlogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
            /*   textviewDescription(
                   originalText = "a very long text that will be truncated at some points",
                   expandAction = "See more",
                   expand = expand,
                   expandActionColor = Color.Blue,
                   limitedMaxLines = 2,
                   animationSpec = spring(),
                   modifier = Modifier
                       .clickable { expand = !expand },
               )*/
            textviewDescription.setOnClickListener {
                callBack(!textviewDescription.expanded)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewBlogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    fun onClick(kFunction1: (Boolean) -> Unit) {
        this.callBack = kFunction1
    }
}