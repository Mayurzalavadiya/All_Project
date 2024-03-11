package com.example.listviewdemo.task7.activity

import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.ActivityDemoBinding
import com.example.listviewdemo.task7.adapter.FirstAdapter
import com.example.listviewdemo.task7.pojo.FirstRecyclerData

class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    private val leftAdapter by lazy { FirstAdapter() }
    private val rightAdapter by lazy { FirstAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFirstAdapter()
        setSecondAdapter()
        setClickListener()
    }

    private fun setFirstAdapter() = with(binding) {
        recyclerViewFirst.addItemDecoration(ItemDecorate())
        recyclerViewFirst.layoutManager = LinearLayoutManager(this@DemoActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewFirst.adapter = leftAdapter
        leftAdapter.addItem(FirstRecyclerData("abc"))
    }

    private fun setSecondAdapter() = with(binding) {
        recyclerViewSecond.addItemDecoration(ItemDecorate())
        recyclerViewSecond.layoutManager = LinearLayoutManager(this@DemoActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewSecond.adapter = rightAdapter
    }

    private fun setClickListener() {
        addItemsClick()
        deleteItem()
        copyLeftToRight()
        copyRightToLeft()
        moveLeftToRight()
        moveRightToLeft()
        switchList()
        clearData()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun clearData() = with(binding) {
        buttonClear.setOnClickListener {
            rightAdapter.clearList()
            leftAdapter.notifyDataSetChanged()
            rightAdapter.notifyDataSetChanged()
            leftAdapter.clearList()
        }
    }

    private fun addItemsClick() {
        binding.buttonAdd.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() = with(binding) {
        val text = edittextName.text.toString().trim()
        if (text.isEmpty()) {
            Toast.makeText(
                this@DemoActivity,
                getString(R.string.validation_please_enter_name),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            edittextName.text = null
            leftAdapter.addItem(FirstRecyclerData(text))
        }
    }


    private fun deleteItem() = with(binding) {
        buttonDelete.setOnClickListener {
            leftAdapter.removeSelectedItem()
            rightAdapter.removeSelectedItem()
        }
    }

    private fun copyLeftToRight() = with(binding) {
        buttonCopyLeftToRight.setOnClickListener {
            val selectedItems = leftAdapter.getSelectedList()

            for (item in selectedItems) {
                rightAdapter.addItem(FirstRecyclerData(item.name))
            }
        }
    }

    private fun copyRightToLeft() = with(binding) {
        buttonCopyRightToLeft.setOnClickListener {
            val selectedItems = rightAdapter.getSelectedList()

            for (item in selectedItems) {
                leftAdapter.addItem(FirstRecyclerData(item.name))
            }
        }
    }

    private fun moveLeftToRight() = with(binding) {
        buttonMoveLeftToRight.setOnClickListener {
            val selectedItems = leftAdapter.getSelectedList()
            if (selectedItems.isNotEmpty()) {
                leftAdapter.removeSelectedItem()
                for (item in selectedItems) {
                    rightAdapter.addItem(FirstRecyclerData(item.name))
                }
            }
        }
    }

    private fun moveRightToLeft() = with(binding) {
        buttonMoveRightToLeft.setOnClickListener {
            val selectedItems = rightAdapter.getSelectedList()
            if (selectedItems.isNotEmpty()) {
                rightAdapter.removeSelectedItem()
                for (item in selectedItems) {
                    leftAdapter.addItem(FirstRecyclerData(item.name))
                }
            }
        }
    }

    private fun switchList() = with(binding) {
        buttonSwitch.setOnClickListener {
            val selectedItemsLeft = leftAdapter.getSelectedList()
            val selectedItemsRight = rightAdapter.getSelectedList()

            if (selectedItemsLeft.isNotEmpty() && selectedItemsRight.isNotEmpty()) {
                leftAdapter.removeSelectedItem()
                rightAdapter.removeSelectedItem()
                leftAdapter.addItem(selectedItemsRight)
                rightAdapter.addItem(selectedItemsLeft)
            }
        }
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._10dp)
        }

    }
}

