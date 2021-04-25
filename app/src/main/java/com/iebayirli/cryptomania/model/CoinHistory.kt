package com.iebayirli.cryptomania.model

import com.squareup.moshi.Json

data class CoinHistory(
    @Json(name = "prices")
    val prices: List<DoubleArray>
)
