package com.example.listviewdemo.demo.list.activity

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.demo.list.adapter.RecyclerAdapterHorizontal
import com.example.listviewdemo.demo.list.adapter.RecyclerAdapterVertical
import com.example.listviewdemo.demo.interfaces.RecyclerClickItem
import com.example.listviewdemo.pojo.RecyclerItemData
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    private val itemList = mutableListOf<RecyclerItemData>()

    inner class ItemDecorator : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)

        }
    }

    inner class ItemDecoratorVertical : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._10dp)
        }

        private val divider =
            ContextCompat.getDrawable(this@RecyclerActivity, R.drawable.recycler_divider)

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childSize = parent.childCount

            for (i in 0 until childSize) {
                val child = parent.getChildAt(i)

                val layoutParams = (child.layoutParams as RecyclerView.LayoutParams)

                val top = child.bottom + layoutParams.bottomMargin
                val bottom = top + (divider?.intrinsicHeight ?: 0)

                divider?.setBounds(left, top, right, bottom)
                divider?.draw(c)
            }
        }
    }

    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList.add(RecyclerItemData("Mayur", R.drawable.facebook))
        itemList.add(RecyclerItemData("Nishita", R.drawable.woman))
        itemList.add(RecyclerItemData("Nishit", R.drawable.ic_android))
        itemList.add(RecyclerItemData("Yug", R.drawable.facebook))
        itemList.add(RecyclerItemData("Pratik", R.drawable.twitter))
        itemList.add(RecyclerItemData("Bhavyesh", R.drawable.facebook))
        itemList.add(RecyclerItemData("Akshay", R.drawable.yoga))
        itemList.add(RecyclerItemData("Deep", R.drawable.facebook))
        itemList.add(RecyclerItemData("Gautam", R.drawable.google_plus))
        itemList.add(RecyclerItemData("Mitul", R.drawable.facebook))
        itemList.add(RecyclerItemData("Mayur", R.drawable.facebook))
        itemList.add(RecyclerItemData("Nishita", R.drawable.woman))
        setRecyclerAdapter(itemList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecyclerAdapter(items: MutableList<RecyclerItemData>) = with(binding) {
        // Horizontal
        recyclerViewHorizontal.adapter = RecyclerAdapterHorizontal(ClickListener())
//        recyclerViewHorizontal.addItemDecoration(DividerItemDecoration(this@RecyclerActivity,DividerItemDecoration.VERTICAL))
        recyclerViewHorizontal.addItemDecoration(ItemDecorator())
        recyclerViewHorizontal.layoutManager =
            LinearLayoutManager(this@RecyclerActivity, LinearLayoutManager.HORIZONTAL, false)
        (recyclerViewHorizontal.adapter as? RecyclerAdapterHorizontal)?.addItem(items)

        // Vertical
        recyclerViewVertical.adapter = RecyclerAdapterVertical(CliCkListenerHorizontal())
        recyclerViewVertical.addItemDecoration(ItemDecoratorVertical())
        recyclerViewVertical.layoutManager =
            LinearLayoutManager(this@RecyclerActivity, LinearLayoutManager.VERTICAL, false)
        (recyclerViewVertical.adapter as RecyclerAdapterVertical).addItem(itemList)
        (recyclerViewVertical.adapter as RecyclerAdapterVertical).notifyDataSetChanged()
    }

    inner class ClickListener : RecyclerClickItem {
        @SuppressLint("NotifyDataSetChanged")
        override fun onClick(item: RecyclerItemData?, v: View?) {

            with(binding) {
                itemList.clear()

                itemList.add(RecyclerItemData("Mayur", item!!.image))
                itemList.add(RecyclerItemData("Nishita", item.image))
                itemList.add(RecyclerItemData("Nishit", item.image))
                itemList.add(RecyclerItemData("Yug", item.image))
                itemList.add(RecyclerItemData("Pratik", item.image))
                itemList.add(RecyclerItemData("Bhavyesh", item.image))
                itemList.add(RecyclerItemData("Akshay", item.image))
                itemList.add(RecyclerItemData("Deep", item.image))
                itemList.add(RecyclerItemData("Gautam", item.image))
                itemList.add(RecyclerItemData("Mitul", item.image))
                itemList.add(RecyclerItemData("Mayur", item.image))


                (recyclerViewVertical.adapter as RecyclerAdapterVertical).apply {
                    clearItems()
                    addItem(itemList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    inner class CliCkListenerHorizontal : RecyclerClickItem {
        override fun onClick(item: RecyclerItemData?, v: View?) {
            // Handle click on horizontal item
        }
    }

    fun showToast(item: RecyclerItemData?) {
        Toast.makeText(this, item?.name, Toast.LENGTH_SHORT).show()
    }
}
