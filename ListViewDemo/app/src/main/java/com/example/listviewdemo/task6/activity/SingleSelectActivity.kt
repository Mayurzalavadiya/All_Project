package com.example.listviewdemo.task6.activity

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.ActivitySingleSelectBinding
import com.example.listviewdemo.task6.adapter.SingleSelectAdapter
import com.example.listviewdemo.task6.pojo.SingleSelectData

class SingleSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleSelectBinding

    private val singleSelectAdapter by lazy { SingleSelectAdapter() }

    val items = mutableListOf<SingleSelectData>()
    private val selectedItems = arrayListOf<SingleSelectData>()
    private val idList = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addItems()
        setItemAdapter()
    }


    private fun setItemAdapter() = with(binding) {
        recyclerViewMultipleSelection.adapter = singleSelectAdapter
        recyclerViewMultipleSelection.layoutManager =
            LinearLayoutManager(this@SingleSelectActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewMultipleSelection.addItemDecoration(ItemDecorate())
        singleSelectAdapter.addItem(items)
    }


    private fun addItems() {
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/c/bcd8b1222581_H-8901030764820.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://www.beverlyhillsmagazine.com/wp-content/uploads/Skincare-for-Men-Anti-aging-Products-for-Men-Beauty-Magazine-Beauty-Supplies-Beauty-Product-For-Men-Beverly-Hills-Magazine-2-1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://cdn.nrf.com/sites/default/files/styles/crop_1027_547/public/2020-09/mens%20cosmetics.png?itok=7EAMtlgN",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://www.themancompany.com/cdn/shop/files/Perfect-Summer-Ready-Kit.jpg?v=1696938056",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-lSTfdrxt3UqN0sgf4pGCuW9tDlE54-AAbwPpI5UV6kY16yzZClZ5p73XhMlJhPiQhyI&usqp=CAU",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://cdn.nrf.com/sites/default/files/styles/crop_1027_547/public/2020-09/mens%20cosmetics.png?itok=7EAMtlgN",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://www.themancompany.com/cdn/shop/files/Perfect-Summer-Ready-Kit.jpg?v=1696938056",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-lSTfdrxt3UqN0sgf4pGCuW9tDlE54-AAbwPpI5UV6kY16yzZClZ5p73XhMlJhPiQhyI&usqp=CAU",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
         SingleSelectData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = resources.getDimensionPixelSize(R.dimen._3dp)
            outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._5dp)
        }
    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onClick(positions: ArrayList<Int>, v: View?) {
//        selectedItems.clear()
//        for (i in 0 until items.size) {
//            if (items[i].isSelected) {
//                selectedItems.add(items[i])
//            }
//        }
//        binding.textViewCount.text =
//            "${resources.getString(R.string.total_item_selected_count)} ${positions.size.toString()}"
//    }

//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        if (count == 0) {
//            binding.textViewCount.visibility = View.VISIBLE
//            binding.recyclerViewMultipleSelection.visibility = View.VISIBLE
//            binding.fragmentContainer.visibility = View.GONE
//        } else{
//            super.onBackPressed()
//        }
//        count++
//
//    }
}