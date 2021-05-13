package com.iebayirli.cryptomania.model

import com.iebayirli.cryptomania.base.ListAdapterItem
import com.squareup.moshi.Json

data class Coin(
        @Json(name = "id")
        override val id: String?,
        @Json(name = "symbol")
        val symbol: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "image")
        val image: String?,
        @Json(name = "current_price")
        val currentPrice: Double?,
        @Json(name = "price_change_percentage_1h_in_currency")
        val changePercent: Double?,
        var isFavourite: Boolean = false
) : ListAdapterItem, java.io.Serializable