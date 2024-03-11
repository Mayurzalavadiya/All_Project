package com.test4.activity

import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test4.R
import com.test4.adapter.CusinesAdapter
import com.test4.adapter.RestaurantAdapter
import com.test4.adapter.TradingAdapter
import com.test4.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = mutableListOf<String>()

        list.add(getString(R.string.presto_eat))

        setTradingAdapter(list)
        setCuisinesAdapter(list)
        setRestaurantAdapter(list)
    }

    private fun setRestaurantAdapter(list: MutableList<String>) = with(binding) {
        recyclerViewRestaurant.adapter = RestaurantAdapter()
        recyclerViewRestaurant.layoutManager =
            LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.VERTICAL, false)

        recyclerViewRestaurant.addItemDecoration(ItemDecorateRestaurant())
        (recyclerViewRestaurant.adapter as? RestaurantAdapter)?.addItem(list)
    }

    private fun setCuisinesAdapter(list: MutableList<String>) = with(binding) {
        recyclerViewCuisines.adapter = CusinesAdapter()
        recyclerViewCuisines.layoutManager =
            LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCuisines.addItemDecoration(ItemDecorate());
        (recyclerViewCuisines.adapter as? CusinesAdapter)?.addItem(list)
    }

    private fun setTradingAdapter(list: MutableList<String>) = with(binding) {

        recyclerViewTrading.adapter = TradingAdapter()
        recyclerViewTrading.layoutManager =
            LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTrading.addItemDecoration(ItemDecorate());
        (recyclerViewTrading.adapter as? TradingAdapter)?.addItem(list)
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = resources.getDimensionPixelSize(R.dimen._5)
            outRect.right = resources.getDimensionPixelSize(R.dimen._5)
        }
    }
 inner class ItemDecorateRestaurant : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = resources.getDimensionPixelSize(R.dimen._10)
        }
    }

    inner class ItemDecoratorVertical : RecyclerView.ItemDecoration() {
        private val divider =
            ContextCompat.getDrawable(this@DashboardActivity, R.drawable.recycler_divider)

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childSize = parent.childCount

            for (i in 0 until childSize) {
                val child = parent.getChildAt(i)

                val layoutParams = (child.layoutParams as RecyclerView.LayoutParams)

//                val top = child.bottom + layoutParams.bottomMargin
//                val bottom = top + (divider?.intrinsicHeight ?: 0)

                divider?.setBounds(left, 0, right, 0)
                divider?.draw(c)
            }
        }
    }
}