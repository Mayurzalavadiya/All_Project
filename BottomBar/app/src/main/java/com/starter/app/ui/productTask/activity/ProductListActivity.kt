package com.starter.app.ui.productTask.activity

import android.os.Bundle
import android.view.View
import com.starter.app.R
import com.starter.app.databinding.ActivityProductListBinding
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.productTask.fragment.ProductListFragment

class ProductListActivity : BaseActivity() {

    private lateinit var binding: ActivityProductListBinding

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun createViewBinding(): View {
        binding = ActivityProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load(ProductListFragment::class.java).replace(false)
    }
}