package com.mvvm.ui.services

import com.mvvm.data.response.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("search")
    suspend fun getCountry(): Country

    @GET("search")
    suspend fun getCountry(@Query("name") name: String): Country

}