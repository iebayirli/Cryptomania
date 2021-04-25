package com.iebayirli.cryptomania.ui.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iebayirli.cryptomania.base.BaseViewModel
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    private val _coinList: MutableLiveData<List<Coin>> = MutableLiveData()
    val coinList: LiveData<List<Coin>>
        get() = _coinList

    fun getCoins(favouritesList: Set<String>) {
        viewModelScope.launch {
            apiCall(coinRepository.getAllCoins(),
                { list ->
                    list.forEach {
                        if (!favouritesList.isNullOrEmpty() && favouritesList.contains(
                                it.id
                            )
                        ) {
                            it.isFavourite = true
                        }
                    }
                    val filteredList = list.filter { it.isFavourite }
                    _coinList.value = filteredList
                }, {
                    Log.e(TAG, "getCoinsError: $it")
                })
        }
    }

    private companion object {
        const val TAG = "FavouritesViewModel"
    }
}