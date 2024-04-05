package com.starter.app.data.service

import com.starter.app.data.URLFactory
import com.starter.app.data.pojo.response.BitCoinResponse
import retrofit2.http.GET

interface BitCoinService {

    @GET(URLFactory.Method.BIT_COIN)
    suspend fun getBitcoin(): BitCoinResponse
}