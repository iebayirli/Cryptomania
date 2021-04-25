package com.iebayirli.cryptomania.model

sealed class ResultCoin<out R> {
    data class Success<out T>(val data: T) : ResultCoin<T>()
    data class Error(val message: String) : ResultCoin<Nothing>()
}
