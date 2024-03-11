package com.example.listviewdemo.demo.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.example.listviewdemo.R

class MyAdapter(private val context: Context, private val list: Array<String>) :
    ArrayAdapter<String>(context, R.layout.list_view, list) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        if (view ==null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.list_view, parent,false)
        }
        val name = view?.findViewById<AppCompatTextView>(R.id.textViewName)
        name?.text = list[position].toString()
        return view!!
    }
}