package com.homey.app.ui.home.myBooking.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.ListViewBookingItemBinding
import com.homey.app.ui.home.myBooking.model.MyBookingData


class BookingListAdapter :
    RecyclerView.Adapter<BookingListAdapter.ViewHolder>() {

    private val itemList = mutableListOf<MyBookingData>()
    lateinit var callBack: (String) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: MutableList<MyBookingData>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListViewBookingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyBookingData) = with(binding) {

            textviewStatus.text = item.status

            var colorResId: Int = R.color.colorPrimary
            when (item.status) {
                "Upcoming" -> {
                    setColor(true)
                    colorResId = R.color.Orange
                    constraint.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.colorPrimary
                        )
                    )
                    textviewStatus.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.Orange
                        )
                    )
                }

                "Completed" -> {
                    setColor(false)
                    colorResId = R.color.lightGreen
                    constraint.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.lightWhite
                        )
                    )
                    textviewStatus.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.lightGreen
                        )
                    )

                }

                "Cancelled" -> {
                    setColor(false)
                    colorResId = R.color.Red
                    constraint.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.lightWhite
                        )
                    )
                    textviewStatus.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.Red
                        )
                    )

                }
            }


            textviewStatus.setBackgroundTintList(
                ContextCompat.getColorStateList(textviewStatus.context, colorResId)
                    ?.withAlpha(20)
            )

            itemView.setOnClickListener { callBack(textviewStatus.text.toString()) }
        }


        fun setColor(isUpcoming: Boolean) = with(binding) {
            if (isUpcoming) {
                textViewCheckIn.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.White
                    )
                )
                textViewCheckOut.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.White
                    )
                )
                textViewStartDate.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.White
                    )
                )
                textViewEndDate.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.White
                    )
                )
                imageviewLine.setImageTintList(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.White
                    )
                )
                textViewArrival.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.White
                    )
                )
            } else {
                textViewCheckIn.setTextColor(ContextCompat.getColor(itemView.context, R.color.Gray))
                textViewCheckOut.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.Gray
                    )
                )
                textViewStartDate.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.PrimaryBlack
                    )
                )
                textViewEndDate.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.PrimaryBlack
                    )
                )
                imageviewLine.setImageTintList(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.Gray
                    )
                )
                textViewArrival.setTextColor(ContextCompat.getColor(itemView.context, R.color.Gray))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewBookingItemBinding.inflate(
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

    fun onClick(kFunction1: (String) -> Unit) {
        this.callBack = kFunction1
    }
}