package com.iebayirli.cryptomania.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iebayirli.cryptomania.base.BaseViewModel
import com.iebayirli.cryptomania.service.CoinPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coinPreferences: CoinPreferences
) : BaseViewModel() {

    private val _favouritesList: MutableLiveData<Set<String>> = MutableLiveData()
    val favouritesList: LiveData<Set<String>>
        get() = _favouritesList

    fun updateFavouriteList(list: Set<String>) = viewModelScope.launch {
        coinPreferences.updateFavouritesList(list)
    }

    init {
        initFavouriteList()
    }

    private fun initFavouriteList() =
        viewModelScope.launch {
            coinPreferences.preferencesFlow
                .collect {
                    _favouritesList.value = it.data
                }
        }

}