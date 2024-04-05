package com.starter.app.ui.apicalldemo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.starter.app.R
import com.starter.app.databinding.ActivityApiDemoBinding
import com.starter.app.ui.apicalldemo.adapter.GetStateAdapter
import com.starter.app.ui.apicalldemo.viewmodel.GetStateViewModel
import com.starter.app.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiDemoActivity : BaseActivity() {

    private lateinit var binding: ActivityApiDemoBinding

    private val getStateAdapter by lazy { GetStateAdapter() }

    private val viewModel: GetStateViewModel by viewModels()

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun createViewBinding(): View {
        binding = ActivityApiDemoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAdapter()
        apiCall()
        observeLiveData()
    }

    private fun setAdapter() = with(binding.recyclerView) {
        layoutManager =
            LinearLayoutManager(this@ApiDemoActivity, LinearLayoutManager.VERTICAL, false)
        adapter = getStateAdapter
    }

    private fun apiCall() {
        viewModel.stateList()
    }

    private fun observeLiveData() {
        viewModel.stateListLiveData.observe(this, {
            getStateAdapter.addItem(it.data)
        })
    }
}