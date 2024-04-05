package com.example.sample26.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sample26.adapter.CategoriesAdapter
import com.example.sample26.model.CategoriesModal
import com.example.sample26.R
import com.example.sample26.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var categoriesList: ArrayList<CategoriesModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerList()
        setClickListener()

    }

    private fun setClickListener() = with(binding) {
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerList() = with(binding){
        categoriesList = ArrayList()
        val layoutManager = GridLayoutManager(this@CategoryActivity, 2)
        binding.recyclerView.layoutManager = layoutManager

        categoriesAdapter = CategoriesAdapter(categoriesList, this@CategoryActivity)
        binding.recyclerView.adapter = categoriesAdapter

        // on below line we are adding data to our list
        categoriesList.add(CategoriesModal("Android Development", R.drawable.burger_1))
        categoriesList.add(CategoriesModal("C++ Development", R.drawable.burger_1))
        categoriesList.add(CategoriesModal("Java Development", R.drawable.burger_1))
        categoriesList.add(CategoriesModal("Python Development", R.drawable.burger_1))
        categoriesList.add(CategoriesModal("JavaScript Development", R.drawable.burger_1))
        categoriesList.add(CategoriesModal("JavaScript Development", R.drawable.burger_1))

        categoriesAdapter.notifyDataSetChanged()

    }
}