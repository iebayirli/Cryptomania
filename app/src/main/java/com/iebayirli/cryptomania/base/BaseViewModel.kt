package com.iebayirli.cryptomania.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iebayirli.cryptomania.model.ResultCoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart


abstract class BaseViewModel : ViewModel() {

    val shouldShowProgressDialog: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    suspend fun <T> apiCall(
        request: Flow<ResultCoin<T>>,
        collect: suspend (T) -> Unit,
        failure: (String) -> Unit
    ) {
        request.onStart {
            shouldShowProgressDialog.postValue(true)
        }.catch {
            shouldShowProgressDialog.postValue(false)
            failure("Catch Error")
        }.collect {
            when (it) {
                is ResultCoin.Success -> collect(it.data)
                is ResultCoin.Error -> failure(it.message)
            }
            shouldShowProgressDialog.postValue(false)
        }
    }
}