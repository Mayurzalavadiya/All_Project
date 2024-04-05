package com.mvvm.ui.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.ui.services.ApiService
import com.mvvm.ui.viewModel.CountryListViewModel
import java.lang.IllegalArgumentException

class CountryListViewModelFactory(private val apiService: ApiService)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryListViewModel::class.java)){
            return CountryListViewModel(apiService) as T
        }
        throw IllegalArgumentException("Type anything useful here as exception")
    }

}   