package com.iebayirli.cryptomania.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

data class CoinDetail(
    @Json(name = "hashing_algorithm")
    val hashingAlgorithm: String?,
    @Json(name = "description")
    val description: Desc?
)

data class Desc(
    @Json(name = "en")
    val desc: String?
) : Serializable

