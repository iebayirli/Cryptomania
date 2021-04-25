package com.iebayirli.cryptomania.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iebayirli.cryptomania.base.BaseViewModel
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.model.CoinDetail
import com.iebayirli.cryptomania.model.CoinHistory
import com.iebayirli.cryptomania.repository.CoinRepository
import com.iebayirli.cryptomania.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    private val _coinDetail: MutableLiveData<CoinDetail> = MutableLiveData()
    val coinDetail: LiveData<CoinDetail>
        get() = _coinDetail

    private val _coinHistory: MutableLiveData<CoinHistory> = MutableLiveData()
    val coinHistory: LiveData<CoinHistory>
        get() = _coinHistory

    fun getCoinDetail(id: String) {
        viewModelScope.launch {
            apiCall(coinRepository.getCoinDetail(id), {
                _coinDetail.value = it
            }, {
                Log.e(TAG, "getCoinDetailError: $it")
            })
        }
    }

    fun getCoinHistory(id: String, daily: String = "1") {
        viewModelScope.launch {
            apiCall(coinRepository.getCoinHistory(id, daily), {
                _coinHistory.value = it
            }, {
                Log.e(TAG, "getCoinHistoryError: $it")
            })
        }
    }

    fun setupUI(id: String) {
        getCoinDetail(id)
        getCoinHistory(id)
    }

    private companion object {
        const val TAG = "DetailViewModel"
    }

}