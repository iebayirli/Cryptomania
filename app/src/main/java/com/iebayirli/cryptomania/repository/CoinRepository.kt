package com.iebayirli.cryptomania.repository

import com.iebayirli.cryptomania.base.BaseRepository
import com.iebayirli.cryptomania.service.CoinService
import javax.inject.Inject


class CoinRepository @Inject constructor(private val coinService: CoinService) : BaseRepository() {

    fun getAllCoins() = safeFlowCall {
        coinService.getCoinList()
    }

    fun getCoinDetail(id: String) = safeFlowCall {
        coinService.getCoinDetail(id)
    }

    fun getCoinHistory(id: String, daily: String) = safeFlowCall {
        coinService.getCoinHistory(id, daily)
    }

}