package com.daggerhilt.ui.activity

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.daggerhilt.databinding.ActivityCountryListBinding
import com.daggerhilt.ui.adapter.CountryAdapter
import com.daggerhilt.ui.base.BaseActivity
import com.daggerhilt.ui.viewModel.CountryListViewModel
import com.restapi.Const
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class CountryListActivity : BaseActivity() {

    private lateinit var binding: ActivityCountryListBinding

    private val countryAdapter by lazy { CountryAdapter() }

    private val viewModel: CountryListViewModel by viewModels()

    override fun createView(): View {
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {

        setAdapter()
        apiCall()
        observeLiveData()
        searchData()
    }

    private fun searchData() = with(binding) {
        var searchJob: Job? = null

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(500L)
                    newText?.let { viewModel.getCountryList(newText) }
                }

                return true
            }
        })
    }


    private fun setAdapter() = with(binding) {
        recyclerView.adapter = countryAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this@CountryListActivity, LinearLayoutManager.VERTICAL, false)
    }

    private fun apiCall() {
        Const.showProgressDialog(this)
        viewModel.getCountryList("")
    }

    private fun observeLiveData() {
        viewModel.countryList.observe(this) {
            countryAdapter.clear()
            countryAdapter.addItem(it)
        }

    }


}