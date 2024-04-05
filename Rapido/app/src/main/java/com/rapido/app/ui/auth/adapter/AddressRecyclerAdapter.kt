package com.rapido.app.ui.auth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rapido.app.data.pojo.request.UpdateAddressRequest
import com.rapido.app.data.pojo.response.AddressResponse
import com.rapido.app.ui.auth.interfaces.ClickListener
import com.rapido.app.ui.auth.viewmodel.GetAddressViewModel
import com.rapido.app.R
import com.rapido.app.databinding.AddressListViewBinding

class AddressRecyclerAdapter(val clickListener: ClickListener, val viewModel: GetAddressViewModel) :
    RecyclerView.Adapter<AddressRecyclerAdapter.ViewHolder>() {

    private val itemsList = mutableListOf<AddressResponse>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: List<AddressResponse>) {
        itemsList.clear()
        this.itemsList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AddressListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddressResponse) = with(binding) {

            textviewCategory.text = item.addressType
            textviewAddress.text = item.address

            if (item.isDefault == 1.0) {
                constraint.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.Grey8pe))
                textviewDefault.setBackgroundResource(R.drawable.bg_button)
            } else {
                textviewDefault.setBackgroundResource(R.drawable.bg_button_view_password)
                constraint.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.white))
            }

            imageviewDelete.setOnClickListener {
                val request = UpdateAddressRequest(
                    requestType = itemView.context.getString(R.string.delete),
                    addressId = itemsList[adapterPosition].id,
                )
                viewModel.updateAddress(request)
            }

            imageviewEdit.setOnClickListener {
                clickListener.click(itemsList[adapterPosition], imageviewEdit)
            }

            textviewDefault.setOnClickListener {
                val request = UpdateAddressRequest(
                    requestType = itemView.context.getString(R.string.set_detault),
                    addressId = itemsList[adapterPosition].id,
                )
                viewModel.updateAddress(request)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AddressListViewBinding.inflate(
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

    @SuppressLint("NotifyDataSetChanged")
    fun clear(){
        itemsList.clear()
        notifyDataSetChanged()
    }
}