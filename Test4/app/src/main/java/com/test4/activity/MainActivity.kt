package com.test4.activity

import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test4.adapter.CategoryAdapter
import com.test4.pojo.CategoryItem
import com.test4.R
import com.test4.adapter.MenuAdapter
import com.test4.databinding.ActivityMainBinding
import com.test4.`interface`.RecyclerClickListener
import com.test4.pojo.MenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainList = mutableListOf<CategoryItem>()
    private val itemList = mutableListOf<MenuItem>()
    private val itemList2 = mutableListOf<MenuItem>()
    private val menuList = mutableListOf<MenuItem>()

    val menuAdapter by lazy {
        MenuAdapter()
    }
    val categoryAdapter by lazy {
        CategoryAdapter(ClickListener())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addItemList()
        addMenuList()
        setNameListAdapter(mainList)
        setListAdapter(itemList)
    }

    private fun addMenuList() {
        mainList.add(CategoryItem(1, "Mayur", itemList))
        mainList.add(CategoryItem(2, "Nishita", itemList2))
        mainList.add(CategoryItem(3, "Nishit", itemList))
        mainList.add(CategoryItem(4, "Yug", itemList))
        mainList.add(CategoryItem(5, "Pratik", itemList2))
        mainList.add(CategoryItem(6, "Bhavyesh", itemList))
        mainList.add(CategoryItem(7, "Akshay", itemList2))
    }

    private fun addItemList() {
        itemList.add(
            MenuItem(
                0,
                R.drawable.hlis,
                "Veg.Manchuriam",
                getString(R.string.description),
                "$ 15.0"
            )
        )
        itemList.add(
            MenuItem(
                1,
                R.drawable.hlis,
                "Veg.Manchuriam",
                getString(R.string.description),
                "$ 15.0"
            )
        )

        itemList.add(
            MenuItem(
                2,
                R.drawable.image_4,
                "Pizza",
                getString(R.string.description),
                "$ 16.0"
            )
        )
        itemList.add(
            MenuItem(
                3,
                R.drawable.image_4,
                "Pizza",
                getString(R.string.description),
                "$ 16.0"
            )
        )
        itemList.add(
            MenuItem(
                4,
                R.drawable.images_3,
                "Pizza",
                getString(R.string.description),
                "$ 16.0"
            )
        )

        itemList2.add(
            MenuItem(
                0,
                R.drawable.images_5,
                "Veg.Manchuriam",
                getString(R.string.description),
                "$ 15.0"
            )
        )
        itemList2.add(
            MenuItem(
                1,
                R.drawable.hlis,
                "Veg.Manchuriam",
                getString(R.string.description),
                "$ 15.0"
            )
        )

        itemList2.add(
            MenuItem(
                2,
                R.drawable.images_5,
                "Pizza",
                getString(R.string.description),
                "$ 16.0"
            )
        )

    }


    private fun setNameListAdapter(items: MutableList<CategoryItem>) = with(binding) {

        //Horizontal
        recyclerViewName.adapter = categoryAdapter
        recyclerViewName.addItemDecoration(ItemDecorator())

        recyclerViewName.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        categoryAdapter.addItem(items)
    }

    inner class ClickListener : RecyclerClickListener {
        @SuppressLint("NotifyDataSetChanged")

        override fun onClick(position: Int, v: View?) {
            menuList.clear()
            menuList.addAll(mainList[position].items)

            menuAdapter.apply {
                clearItems()
                addItem(menuList)
            }
        }
    }

    private fun setListAdapter(itemList: MutableList<MenuItem>) = with(binding) {

        //Horizontal
        recyclerViewItem.adapter = menuAdapter
        recyclerViewItem.addItemDecoration(ItemDecorator())
        recyclerViewItem.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        menuAdapter.addItem(itemList)

    }

    inner class ItemDecorator : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = resources.getDimensionPixelSize(R.dimen._10)
            outRect.right = resources.getDimensionPixelSize(R.dimen._10)
            outRect.top = resources.getDimensionPixelSize(R.dimen._10)
        }
    }
}

