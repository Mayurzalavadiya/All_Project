package com.homehaven.app.ui.home.adapter

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homehaven.app.R
import com.homehaven.app.databinding.SpecialofferListViewBinding
import com.homehaven.app.ui.home.Interfaces.ClickListener
import com.homehaven.app.ui.home.model.SpecialOfferData


//SpecialOfferAdapter
//itemsList
//SpecialOfferData
//SpecialofferListViewBinding

class SpecialOfferAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<SpecialOfferAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<SpecialOfferData>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<SpecialOfferData>) {
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: SpecialofferListViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(item: SpecialOfferData) = with(binding) {
            imageViewMain.setImageResource(item.image)
            textviewName.text = item.productName
            textviewPrice.text =
                itemView.context.getString(R.string.textview_price, item.productPrice)

            val oldPriceText =
                itemView.context.getString(R.string.textview_oldprice, item.productOldPrice)
            val spannableString = SpannableString(oldPriceText)
            spannableString.setSpan(
                StrikethroughSpan(),
                0,
                oldPriceText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textviewOldPrice.text = spannableString

            textviewPerOff.text =
                itemView.context.getString(R.string.textview_percantage_off, item.percentage)

            textviewRating.text = item.productRate


            itemView.setOnClickListener(this@ViewHolder)

        }

        override fun onClick(v: View?) {
            clickListener.onClick(adapterPosition, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SpecialofferListViewBinding.inflate(
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

}