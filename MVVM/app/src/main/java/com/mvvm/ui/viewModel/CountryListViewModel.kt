package com.mvvm.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.data.response.CountryItem
import com.mvvm.ui.services.ApiService
import com.restapi.Const
import kotlinx.coroutines.launch

class CountryListViewModel(private val apiService: ApiService) : ViewModel() {

    var countryList = MutableLiveData<ArrayList<CountryItem>>()


    fun getCountryList(name: String) {
//        val apiService = Const.create()
        viewModelScope.launch(Const.scopeExceptionHandling) {
            val countries = apiService.getCountry(name)
            Const.hideProgressDialog()
            countryList.value = countries
//            countryList.postValue(countries)
        }
    }
}