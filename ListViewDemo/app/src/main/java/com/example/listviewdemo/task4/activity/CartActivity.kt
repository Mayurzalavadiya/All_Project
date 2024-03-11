package com.example.listviewdemo.task4.activity

import android.content.Intent
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.listviewdemo.R
import com.example.listviewdemo.task4.adapter.CartAdapter
import com.example.listviewdemo.databinding.ActivityCartBinding
import com.example.listviewdemo.task4.pojo.ProductItemData

@Suppress("DEPRECATION")
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private var products: ArrayList<ProductItemData>? = null

    private val cartAdapter by lazy { CartAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        products = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableArrayListExtra("ProductList", ProductItemData::class.java)
        } else {
            intent.getParcelableArrayListExtra("ProductList")
        }


        setItemAdapter(products)
    }


    private fun setItemAdapter(productList: ArrayList<ProductItemData>?) = with(binding) {
        recyclerViewSelectProduct.adapter = cartAdapter
        recyclerViewSelectProduct.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewSelectProduct.addItemDecoration(ItemDecorate())
        cartAdapter.addItem(productList)
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.top = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.left = resources.getDimensionPixelSize(R.dimen._5dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._5dp)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent()
        intent.putParcelableArrayListExtra("UpdatedProductList", cartAdapter.list)
        setResult(RESULT_OK, intent)
        super.onBackPressed()
    }
}