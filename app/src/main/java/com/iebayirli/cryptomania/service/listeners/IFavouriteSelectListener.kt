package com.iebayirli.cryptomania.service.listeners

import android.view.View
import com.iebayirli.cryptomania.model.Coin

interface IFavouriteSelectListener {
    fun favouriteAdded(view: View, coin: Coin)
}