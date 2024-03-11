package com.mvvm.ui.activity

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.databinding.ActivityCountryListBinding
import com.mvvm.di.App
import com.mvvm.ui.adapter.CountryAdapter
import com.mvvm.ui.base.BaseActivity
import com.mvvm.ui.manager.CountryListViewModelFactory
import com.mvvm.ui.services.ApiService
import com.mvvm.ui.viewModel.CountryListViewModel
import com.restapi.Const
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

class CountryListActivity : BaseActivity() {

    private lateinit var binding: ActivityCountryListBinding

    private val countryAdapter by lazy { CountryAdapter() }

    @Inject
    lateinit var apiService: ApiService
    
    private val viewModel by lazy { ViewModelProvider(this, CountryListViewModelFactory(apiService))[CountryListViewModel::class.java] }

    override fun createView(): View {
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {

        val application = application
        if (application is App) {
            application.appComponent.inject(this)
        }
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
                    newText?.let { viewModel.getCountryList(it) }
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