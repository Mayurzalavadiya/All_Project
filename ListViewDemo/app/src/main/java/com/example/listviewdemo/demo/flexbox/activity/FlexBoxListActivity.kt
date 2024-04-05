package com.example.listviewdemo.demo.flexbox.activity

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.demo.flexbox.adapter.RecyclerAdapterFlexBox
import com.example.listviewdemo.demo.interfaces.RecyclerClickItem
import com.example.listviewdemo.pojo.RecyclerFlexItemData
import com.example.listviewdemo.pojo.RecyclerItemData
import com.example.listviewdemo.databinding.ActivityFlexBoxListBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class FlexBoxListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFlexBoxListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlexBoxListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = mutableListOf<RecyclerFlexItemData>()

        items.add(RecyclerFlexItemData("Mayur Zalavadiya"))
        items.add(RecyclerFlexItemData("fsdf sfsd "))
        items.add(RecyclerFlexItemData("sdfdsfds"))
        items.add(RecyclerFlexItemData("sdfd"))
        items.add(RecyclerFlexItemData("sdf s fsd fsdf sdf sdf ds"))
        items.add(RecyclerFlexItemData(" dsf sdf sdf dsf"))
        items.add(RecyclerFlexItemData("sdfd dfdsf sdf sdfsdf sdf sdf dsfsd fdsf sdf"))

        setItemAdapter(items)
    }

    private fun setItemAdapter(item: MutableList<RecyclerFlexItemData>) = with(binding) {

        recyclerViewFlexList.adapter = RecyclerAdapterFlexBox()
//        recyclerViewFlexList.addItemDecoration(DividerItemDecoration(this@FlexBoxListActivity,DividerItemDecoration.VERTICAL))
        recyclerViewFlexList.addItemDecoration(ItemDecorator())

        val flexBox = FlexboxLayoutManager(this@FlexBoxListActivity)
        flexBox.flexWrap = FlexWrap.WRAP
        flexBox.flexDirection = FlexDirection.ROW
        flexBox.justifyContent = JustifyContent.CENTER
        flexBox.alignItems = AlignItems.CENTER

        recyclerViewFlexList.layoutManager = flexBox
        (recyclerViewFlexList.adapter as? RecyclerAdapterFlexBox)?.addItem(item)

    }

    inner class CliCkListener : RecyclerClickItem {
        override fun onClick(item: RecyclerItemData?, v: View?) {
            // Handle click on horizontal item
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

            outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.bottom = resources.getDimensionPixelSize(R.dimen._10dp)

        }
    }
}