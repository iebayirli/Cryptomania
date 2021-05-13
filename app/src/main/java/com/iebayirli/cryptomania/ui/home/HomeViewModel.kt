package com.iebayirli.cryptomania.ui.home


import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iebayirli.cryptomania.base.BaseViewModel
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.repository.CoinRepository
import com.iebayirli.cryptomania.service.listeners.IQueryTextChangedListener
import com.iebayirli.cryptomania.utils.LiveEvent
import com.iebayirli.cryptomania.utils.compare
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val coinRepository: CoinRepository
) : BaseViewModel(), IQueryTextChangedListener {

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>> = _coinList

    private var tempQueryList: List<Coin> = listOf()

    val queryEvent: LiveEvent<Boolean> = LiveEvent()

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
                        _coinList.value = list
                        tempQueryList = _coinList.value!!
                    }, {
                Log.e(TAG, "getCoinsError: $it")
            })
        }
    }

    val queryTextChangedListener: IQueryTextChangedListener? = this
    override fun queryTextChanged(query: StateFlow<String>) {
        viewModelScope.launch {
            query.debounce(300)
                    .filter { query ->
                        if (query.isEmpty()) {
                            queryEvent.value = true
                            return@filter false
                        } else {
                            return@filter true
                        }
                    }.distinctUntilChanged()
                    .collect { query ->
                        updateList(tempQueryList.filter { it.id!!.compare(query) })
                    }
        }
    }

    private fun updateList(filteredList: List<Coin>) {
        _coinList.value = filteredList
    }

    private companion object {
        const val TAG = "HomeViewModel"
    }
}

