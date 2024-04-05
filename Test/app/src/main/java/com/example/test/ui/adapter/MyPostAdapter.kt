package com.example.test.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.pojo.MyQuotes
import com.example.test.databinding.MyPostListBinding
import com.example.test.ui.base.Const
import com.example.test.ui.interfaces.EditListener

class MyPostAdapter(val editListener: EditListener) :
    RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {
    private var quotesList = mutableListOf<MyQuotes>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(itemList: MutableList<MyQuotes>) {
        this.quotesList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MyPostListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        fun bind(item: MyQuotes) = with(binding) {
            imageViewImage.setImageResource(item.image)
            textViewTitle.text = item.title
            textViewDes.text = item.description
            textViewLocation.text = item.location

            if (item.like) {
                pink(imageViewLike)
            }
            if (item.save) {
                pink(imageViewSave)
            }

            setPopUpMenu()
            setClickListener()
        }


        private fun setPopUpMenu() = with(binding) {
            val popupMenu = PopupMenu(
                itemView.context,
                imageViewMenu
            )

            popupMenu.menu.add(Menu.NONE, 0, 0, R.string.edit)
            popupMenu.menu.add(Menu.NONE, 1, 1, R.string.share)
            popupMenu.menu.add(Menu.NONE, 2, 2, R.string.delete)


            popupMenu.setOnMenuItemClickListener { menuItem ->
                val id = menuItem.itemId
                when (id) {
                    0 -> {
                        editListener.onClick(adapterPosition, false, itemView)
                    }

                    1 -> {
                        Const.showMessage(
                            itemView.context,
                            itemView.context.getString(R.string.share)
                        )
                    }

                    2 -> {
                        editListener.onClick(adapterPosition, true, itemView)
                    }
                }

                false

            }
            imageViewMenu.setOnClickListener {
                popupMenu.show()
            }
        }

        private fun setClickListener() = with(binding) {


            imageViewLike.setOnClickListener {
                quotesList[adapterPosition].like = !quotesList[adapterPosition].like

                if (quotesList[adapterPosition].like) {
                    pink(imageViewLike)
                } else {
                    white(imageViewLike)
                }
            }

            imageViewSave.setOnClickListener {
                quotesList[adapterPosition].save = !quotesList[adapterPosition].save

                if (quotesList[adapterPosition].save) {
                    pink(imageViewSave)
                } else {
                    white(imageViewSave)
                }
            }
        }


        private fun white(view: ImageView) {
            view.setColorFilter(
                ContextCompat.getColor(
                    itemView.context, R.color.white
                )
            )
        }

        private fun pink(view: ImageView) {
            view.setColorFilter(
                ContextCompat.getColor(
                    itemView.context, R.color.Pink
                )
            )
        }


        override fun onClick(v: View?) {
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MyPostListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = quotesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quotesList[position])
    }

    fun clear() {
        quotesList.clear()
    }

    fun deleteItem(position: Int) {
        quotesList.removeAt(position)
        notifyItemChanged(position)
    }
}