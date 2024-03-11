package com.example.listviewdemo.task2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.listviewdemo.R
import com.example.listviewdemo.task2.adapter.StaggeredAdapter
import com.example.listviewdemo.databinding.ActivityGridBinding
import com.example.listviewdemo.task1.interfaces.RecyclerGridClickItem
import com.example.listviewdemo.pojo.ItemData

class StaggeredActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridBinding
    private var firstPosition = 0

    private var lastPosition = 0

    val adapter by lazy {
        StaggeredAdapter(ClickItem())
    }
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
            Log.i("TAG", "setClickListener: $firstPosition $lastPosition")
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
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
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

}