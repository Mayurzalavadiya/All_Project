package com.tinder.app.ui.activity.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tinder.app.databinding.ItemViewBinding
import com.tinder.app.ui.activity.model.UsersData

/**
 * Created by manel on 9/5/2017.
 */
class arrayAdapter(context: Context, resourceId: Int, items: List<UsersData?>) : ArrayAdapter<UsersData?>(context, resourceId, items) {

    lateinit var binding: ItemViewBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val card_item = getItem(position)
        Log.e("TAG", "getView: $card_item", )
        if (convertView == null) {
            binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        binding.textview.text = card_item?.name
        if (card_item != null) {
            binding.imageview.setImageResource(card_item.image)
        }
        return binding.root
    }

}
