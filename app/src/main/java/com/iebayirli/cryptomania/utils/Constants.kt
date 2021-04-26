package com.iebayirli.cryptomania.utils

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.iebayirli.cryptomania.model.TimeInterval

object Constants {
    const val BASE_URL = "https://api.coingecko.com/api/v3/"

    const val SHARED_PREF_NAME = "Cryptomania_Prefs"

    const val COINS_LIST = "coins/markets"
    const val COIN_HISTORY = "coins/{id}/market_chart"
    const val COIN_DETAIL = "coins/{id}"


    private const val DAILY_TITLE = "Daily price change"
    private const val MONTHLY_TITLE = "Monthly price change"
    private const val FOURTEEN_DAY_TITLE = "14-Day price change"
    private const val MAX_TITLE = "Max price change"


    val timeIntervalList = listOf<TimeInterval>(
            TimeInterval("1", DAILY_TITLE, true),
            TimeInterval("14", FOURTEEN_DAY_TITLE, false),
            TimeInterval("30", MONTHLY_TITLE, false),
            TimeInterval("max", MAX_TITLE, false),
    )

}