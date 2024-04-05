package com.daggerhilt.ui.services

import com.daggerhilt.data.response.Country
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("search")
    suspend fun getCountry(): Country

    @GET("search")
    suspend fun getCountry(@Query("name") name: String): Country

}