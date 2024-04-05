package com.example.listviewdemo.task4.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.task4.adapter.ProductAdapter
import com.example.listviewdemo.databinding.ActivityProductBinding
import com.example.listviewdemo.task4.Const.REQUEST_CART_ACTIVITY
import com.example.listviewdemo.task4.interfaces.ProductItemClick
import com.example.listviewdemo.task4.pojo.ProductItemData

@Suppress("DEPRECATION")
class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    private val productList = mutableListOf<ProductItemData>()
    private var selectItemList = ArrayList<ProductItemData>()

    private val productAdapter by lazy {
        ProductAdapter(ClickItem())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        selectItemList = if (Build.VERSION.SDK_INT >= 33) ({
//            intent.getParcelableArrayListExtra("ProductList", ProductItemData::class.java)
//        })!! else ({
//            intent.getParcelableArrayListExtra("ProductList")
//        })!!
        addProductItems()
        setItemAdapter(productList)
        setClickListener()
    }


    @SuppressLint("SuspiciousIndentation")
    private fun setClickListener() = with(binding) {
        imageViewCart.setOnClickListener {
            val intent = Intent(this@ProductActivity, CartActivity::class.java)
            intent.putParcelableArrayListExtra("ProductList", selectItemList)
            startActivityForResult(intent, REQUEST_CART_ACTIVITY)
        }
    }

    private fun setItemAdapter(productList: MutableList<ProductItemData>) = with(binding) {
        recyclerViewProduct.adapter = productAdapter
        recyclerViewProduct.layoutManager = GridLayoutManager(this@ProductActivity, 2)
        recyclerViewProduct.addItemDecoration(ItemDecorate())
        productAdapter.addItem(productList)
    }

    private fun addProductItems() {
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/b/c/bcd8b1222581_H-8901030764820.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://www.beverlyhillsmagazine.com/wp-content/uploads/Skincare-for-Men-Anti-aging-Products-for-Men-Beauty-Magazine-Beauty-Supplies-Beauty-Product-For-Men-Beverly-Hills-Magazine-2-1.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://cdn.nrf.com/sites/default/files/styles/crop_1027_547/public/2020-09/mens%20cosmetics.png?itok=7EAMtlgN",
            )
        )
        productList.add(
            ProductItemData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://www.themancompany.com/cdn/shop/files/Perfect-Summer-Ready-Kit.jpg?v=1696938056",
            )
        )
        productList.add(
            ProductItemData(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-lSTfdrxt3UqN0sgf4pGCuW9tDlE54-AAbwPpI5UV6kY16yzZClZ5p73XhMlJhPiQhyI&usqp=CAU",
            )
        )
        productList.add(
            ProductItemData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/9/7/971ae74ANOAA00000002_1ml.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/5/6/56e65cdMAYBE00000403_1.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/0/0/0053420NYKAC00000072_1new.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
            )
        )
        productList.add(
            ProductItemData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
            )
        )
    }


    inner class ClickItem : ProductItemClick {
        override fun onClick(position: Int, v: View?) {
            selectItemList.add(productList[position])
            setSelectItemCount()
        }

    }

    private fun setSelectItemCount() = with(binding) {
        textViewCount.text = selectItemList.size.toString()
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CART_ACTIVITY && resultCode == RESULT_OK) {
            val updatedProductList =
                intent?.getParcelableArrayListExtra<ProductItemData>("UpdatedProductList")
            selectItemList.clear()
            if (updatedProductList != null) {
                selectItemList.addAll(updatedProductList)
            }
            setSelectItemCount()
        }
    }
}