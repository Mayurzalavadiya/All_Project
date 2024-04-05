package com.example.listviewdemo.task8.activity

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.ActivityTask8Binding
import com.example.listviewdemo.task8.adapter.ProductAdapter
import com.example.listviewdemo.task8.adapter.BillAdapter
import com.example.listviewdemo.task8.interfaces.ClickListener
import com.example.listviewdemo.task8.pojo.ProductBillData

class Task8Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask8Binding

    private val productAdapter by lazy { ProductAdapter(ProductItemClick()) }
    private val billAdapter by lazy { BillAdapter(BillItemClick()) }

    var id = 0
    var percentage = 0
    private var totalPrice = 0.0
    private var discountPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpinner()
        setProductAdapter()
        setBillAdapter()
        setClickListener()
    }

    private fun setSpinner() = with(binding) {

//        val discount = resources.getStringArray( R.array.Discount)
//
//        val adapter = ArrayAdapter(
//            this@Task8Activity,
//            android.R.layout.simple_spinner_dropdown_item,  discount
//        )
//        spinnerDiscount.adapter = adapter

            ArrayAdapter.createFromResource(
                this@Task8Activity,
                R.array.Discount,
                R.layout.custom_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDiscount.adapter = adapter
            }

        spinnerDiscount.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                percentage = parent.getItemAtPosition(position).toString().toInt()
                setDiscount()
                setFinalPrice()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun setProductAdapter() = with(binding) {
        recyclerViewProduct.addItemDecoration(ItemDecorate())
        recyclerViewProduct.layoutManager =
            LinearLayoutManager(this@Task8Activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewProduct.adapter = productAdapter
        productAdapter.addItem(ProductBillData(id, "Potato", 20.0))
    }

    private fun setBillAdapter() = with(binding) {
        recyclerViewBill.addItemDecoration(ItemDecorate())
        recyclerViewBill.layoutManager =
            LinearLayoutManager(this@Task8Activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewBill.adapter = billAdapter
    }

    private fun setClickListener() {
        binding.buttonAdd.setOnClickListener {
            checkValidation()
        }
    }


    private fun checkValidation() = with(binding) {
        val text = edittextName.text.toString().trim()
        val price = edittextPrice.text.toString().trim()

        if (text.isEmpty()) {
            Toast.makeText(
                this@Task8Activity,
                getString(R.string.validation_please_enter_name),
                Toast.LENGTH_SHORT
            ).show()
        } else if (price.isEmpty()) {
            Toast.makeText(
                this@Task8Activity,
                getString(R.string.validation_please_enter_price),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            id++
            productAdapter.addItem(ProductBillData(id, text, price.toDouble()))
            edittextName.text = null
            edittextPrice.text = null
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

    inner class ProductItemClick : ClickListener {
        override fun onClick(position: Int, v: View?) {
            val selectedItems = productAdapter.getSelectedList(position)
            if (selectedItems.isSelected) {
                billAdapter.addItem(selectedItems)
            } else {
                billAdapter.removeItem(selectedItems.id)
            }
            setTotalPrice()
            setDiscount()
            setFinalPrice()
        }
    }

    private fun setFinalPrice() = with(binding) {
        val finalPrice = totalPrice - discountPrice
        textViewFinalPrices.text = finalPrice.toString()
    }

    private fun setDiscount() = with(binding) {
        discountPrice = 0.0
        discountPrice = totalPrice * percentage / 100
        textViewDiscountPrices.text = discountPrice.toString()

    }

    private fun setTotalPrice() = with(binding) {
        totalPrice = 0.0

        for (item in billAdapter.getList()) {
            totalPrice += item.price
        }
        textViewTotalPrices.text = totalPrice.toString()
    }

    inner class BillItemClick : ClickListener {
        override fun onClick(position: Int, v: View?) {
            val list = billAdapter.getList()

            productAdapter.removeCheck(list[position].id)
            billAdapter.getList().removeAt(position)
            billAdapter.notifyItemRemoved(position)

            setTotalPrice()
            setDiscount()
            setFinalPrice()
        }

    }

}


