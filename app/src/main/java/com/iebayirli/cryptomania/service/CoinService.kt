package com.iebayirli.cryptomania.service

import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.model.CoinDetail
import com.iebayirli.cryptomania.model.CoinHistory
import com.iebayirli.cryptomania.utils.Constants.COINS_LIST
import com.iebayirli.cryptomania.utils.Constants.COIN_DETAIL
import com.iebayirli.cryptomania.utils.Constants.COIN_HISTORY
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinService {

    @GET(COINS_LIST)
    suspend fun getCoinList(
        @Query("vs_currency") includePlatform: String = "usd",
        @Query("price_change_percentage") changePercent: String = "1h"
    ): Response<List<Coin>>


    @GET(COIN_HISTORY)
    suspend fun getCoinHistory(
        @Path("id") id: String,
        @Query("days") days: String,
        @Query("vs_currency") includePlatform: String = "usd"
    ): Response<CoinHistory>

    @GET(COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") id: String
    ): Response<CoinDetail>



}