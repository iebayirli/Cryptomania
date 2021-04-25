package com.iebayirli.cryptomania.service

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.iebayirli.cryptomania.model.ResultCoin
import com.iebayirli.cryptomania.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CoinPreferences @Inject constructor(@ApplicationContext val context: Context) {

    // At the top level of your kotlin file:
    private val Context.dataStore by preferencesDataStore(
        name = Constants.SHARED_PREF_NAME
    )

    val preferencesFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences $exception")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val favourites =
                ResultCoin.Success(preferences[PreferencesKeys.FAVOURITES_LIST] ?: mutableSetOf())
            favourites
        }

    suspend fun updateFavouritesList(list: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.FAVOURITES_LIST] = list
        }
    }

    private object PreferencesKeys {
        val FAVOURITES_LIST = stringSetPreferencesKey("favourites")
    }

    private companion object {
        private const val TAG = "CoinPreferences"
    }
}