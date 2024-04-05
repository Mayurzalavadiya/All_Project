package com.example.listviewdemo.task3.activity

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.task3.adapter.GridAdapter
import com.example.listviewdemo.databinding.ActivityGridBinding
import com.example.listviewdemo.task1.interfaces.RecyclerGridClickItem
import com.example.listviewdemo.pojo.ItemData

class GridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridBinding
    private var firstPosition = 0
    private var lastPosition = 0

    val adapter by lazy {
        GridAdapter()
    }

    //    val arrayImage = arrayListOf(
//        R.drawable.image1,
//        R.drawable.image2,
//        R.drawable.image3,
//        R.drawable.image4,
//        R.drawable.image5,
//        R.drawable.image6,
//        R.drawable.image7,
//        R.drawable.image8,
//        R.drawable.image9,
//        R.drawable.image10
//    )
    private val imageList = mutableListOf<ItemData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addItemsList()
        setAdapter(imageList)
        setClickListener()
        lastPosition = imageList.size
    }

    private fun setClickListener() {
        addItems()
    }


    private fun addItems() = with(binding) {
        buttonAdd.setOnClickListener {
            firstPosition = lastPosition
            lastPosition = firstPosition + imageList.size
            adapter.addItemNew(imageList)
            adapter.notifyItemRangeInserted(firstPosition, lastPosition)

        }
    }

    private fun addItemsList() {
        imageList.add(ItemData(R.drawable.image1))
        imageList.add(ItemData(R.drawable.image2))
        imageList.add(ItemData(R.drawable.image3))
        imageList.add(ItemData(R.drawable.image4))
        imageList.add(ItemData(R.drawable.image5))
        imageList.add(ItemData(R.drawable.image6))
        imageList.add(ItemData(R.drawable.image7))
        imageList.add(ItemData(R.drawable.image8))
        imageList.add(ItemData(R.drawable.image9))
        imageList.add(ItemData(R.drawable.image10))
    }

    private fun setAdapter(imageList: MutableList<ItemData>) = with(binding) {
        recyclerView.adapter = adapter

        val gridLayoutManager =
            GridLayoutManager(this@GridActivity, 2, GridLayoutManager.VERTICAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = if (position % 3 == 0) 2 else 1
        }

        recyclerView.layoutManager = gridLayoutManager

        recyclerView.addItemDecoration(ItemDecorate())

        adapter.addItem(imageList)

    }

    inner class ClickItem : RecyclerGridClickItem {
        override fun onClick(item: ItemData?, v: View?, position: Int) {
            showData(position)
        }
    }

    fun showData(item: Int) {
        Toast.makeText(this, "item $item", Toast.LENGTH_SHORT).show()
    }


    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.bottom = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.left = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._5dp)
        }

    }
}