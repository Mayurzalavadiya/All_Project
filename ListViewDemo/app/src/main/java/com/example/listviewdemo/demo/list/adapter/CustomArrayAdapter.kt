package com.example.listviewdemo.demo.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.example.listviewdemo.R

class CustomArrayAdapter(private val list: ArrayList<Int>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val view: View?

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.list_view, parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.itemName?.text = list[position].toString()

//        setClickListener(viewHolder,parent,position)
        setLongClickListener(viewHolder,parent,position)


        return view!!
    }

    private fun setLongClickListener(
        viewHolder: ViewHolder,
        parent: ViewGroup?,
        position: Int
    ) {
        viewHolder.itemName?.setOnLongClickListener{
            Toast.makeText(parent?.context, list[position].toString(), Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun setClickListener(viewHolder: ViewHolder, parent: ViewGroup?, position: Int) {
        viewHolder.itemName?.setOnClickListener{
            Toast.makeText(parent?.context, list[position].toString(), Toast.LENGTH_SHORT).show()
        }
    }


    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p: Int): Any {
        return p
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ViewHolder(view: View?) {
        val itemName = view?.findViewById<AppCompatTextView>(R.id.textViewName)
    }
}