package com.iebayirli.cryptomania.service.listeners

import com.iebayirli.cryptomania.model.Coin

interface IItemSelectListener {
    fun itemSelected(coin: Coin)
}