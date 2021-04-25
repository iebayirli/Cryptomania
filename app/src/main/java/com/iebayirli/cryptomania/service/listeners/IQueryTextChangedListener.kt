package com.iebayirli.cryptomania.service.listeners

import kotlinx.coroutines.flow.StateFlow

interface IQueryTextChangedListener {
    fun queryTextChanged(query: StateFlow<String>)
}