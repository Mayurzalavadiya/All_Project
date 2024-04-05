package com.example.listviewdemo.demo.staggered.activity

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.listviewdemo.R
import com.example.listviewdemo.demo.staggered.adapter.RecyclerAdapterStaggered
import com.example.listviewdemo.databinding.ActivityStaggeredListBinding
import com.example.listviewdemo.demo.interfaces.RecyclerClickItem
import com.example.listviewdemo.pojo.RecyclerItemData


class StaggeredListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStaggeredListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaggeredListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val items = mutableListOf<RecyclerItemData>()
        items.add(RecyclerItemData("Mayur", R.drawable.facebook))
        items.add(RecyclerItemData("Nishita", R.drawable.image_1))
        items.add(RecyclerItemData("Nishit", R.drawable.ic_android))
        items.add(RecyclerItemData("Yug", R.drawable.facebook))
        items.add(RecyclerItemData("Pratik", R.drawable.twitter))
        items.add(RecyclerItemData("Bhavyesh", R.drawable.facebook))
        items.add(RecyclerItemData("Akshay", R.drawable.yoga))
        items.add(RecyclerItemData("Deep", R.drawable.image_1))
        items.add(RecyclerItemData("Gautam", R.drawable.google_plus))
        items.add(RecyclerItemData("Mitul", R.drawable.facebook))
        items.add(RecyclerItemData("Mayur", R.drawable.image_1))
        items.add(RecyclerItemData("Nishita", R.drawable.woman))
        items.add(RecyclerItemData("Nishit", R.drawable.ic_android))
        items.add(RecyclerItemData("Yug", R.drawable.yoga))
        items.add(RecyclerItemData("Pratik", R.drawable.image_1))
        items.add(RecyclerItemData("Bhavyesh", R.drawable.facebook))
        items.add(RecyclerItemData("Akshay", R.drawable.yoga))
        items.add(RecyclerItemData("Deep", R.drawable.facebook))
        items.add(RecyclerItemData("Gautam", R.drawable.google_plus))
        items.add(RecyclerItemData("Mitul", R.drawable.facebook))
        items.add(RecyclerItemData("Mayur", R.drawable.facebook))
        items.add(RecyclerItemData("Nishita", R.drawable.image_1))
        items.add(RecyclerItemData("Nishit", R.drawable.ic_android))
        items.add(RecyclerItemData("Yug", R.drawable.facebook))
        items.add(RecyclerItemData("Pratik", R.drawable.twitter))
        items.add(RecyclerItemData("Bhavyesh", R.drawable.image_1))
        items.add(RecyclerItemData("Akshay", R.drawable.yoga))
        items.add(RecyclerItemData("Deep", R.drawable.woman))
        items.add(RecyclerItemData("Gautam", R.drawable.google_plus))
        items.add(RecyclerItemData("Mitul", R.drawable.facebook))


        with(binding) {
            recyclerStaggered.adapter = RecyclerAdapterStaggered(ItemClick())
//            recyclerStaggered.addItemDecoration(DividerItemDecoration(this@StaggeredListActivity,DividerItemDecoration.VERTICAL))
            recyclerStaggered.addItemDecoration(ItemDecorator())
            val staggered = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)

            recyclerStaggered.layoutManager = staggered

            (recyclerStaggered.adapter as? RecyclerAdapterStaggered)?.addItem(items)
        }
    }

    inner class ItemClick : RecyclerClickItem {
        override fun onClick(item: RecyclerItemData?, v: View?) {

        }
    }

    inner class ItemDecorator : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.left = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.bottom = resources.getDimensionPixelSize(R.dimen._10dp)

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
            ContextCompat.getDrawable(this@StaggeredListActivity, R.drawable.recycler_divider)

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            val dividerLeft = 32
            val dividerRight = parent.width - 32

            val childSize = parent.childCount

            for (i in 0 until childSize) {
                val child = parent.getChildAt(i)

                val layoutParams = (child.layoutParams as RecyclerView.LayoutParams)

                val dividerTop: Int = child.bottom + layoutParams.bottomMargin
                val bottom = dividerTop + (divider?.intrinsicHeight ?: 0)

                divider?.setBounds(dividerLeft, dividerTop, dividerRight, bottom)
                divider?.draw(c)
            }
        }
    }
}