package com.iebayirli.cryptomania.utils

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {
    const val BASE_URL = "https://api.coingecko.com/api/v3/"

    const val SHARED_PREF_NAME = "Cryptomania_Prefs"

    const val COINS_LIST = "coins/markets"
    const val COIN_HISTORY = "coins/{id}/market_chart"
    const val COIN_DETAIL = "coins/{id}"
}