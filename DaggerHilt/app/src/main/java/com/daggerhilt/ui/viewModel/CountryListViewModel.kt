package com.daggerhilt.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daggerhilt.ui.services.ApiService
import com.daggerhilt.data.response.CountryItem
import com.restapi.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    var countryList = MutableLiveData<ArrayList<CountryItem>>()

    fun getCountryList(name:String) {
        viewModelScope.launch(Const.scopeExceptionHandling) {
            val countries = apiService.getCountry(name)
            Const.hideProgressDialog()
            countryList.value = countries
        }
    }
}