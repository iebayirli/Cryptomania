package com.iebayirli.cryptomania.utils

import android.view.View
import com.google.android.material.imageview.ShapeableImageView
import com.iebayirli.cryptomania.R
import com.iebayirli.cryptomania.model.Coin


object Utils {

    fun setFavoriteClickedUI(
        favouritesList: MutableSet<String>,
        view: View,
        coin: Coin
    ): MutableSet<String> {
        if (!favouritesList.isNullOrEmpty()) {
            if (favouritesList.contains(coin.id)) {
                (view as ShapeableImageView).setImageResource(R.drawable.ic_favourite_border)
                favouritesList.remove(coin.id)
            } else {
                favouritesList.add(coin.id!!)
                (view as ShapeableImageView).setImageResource(R.drawable.ic_favourite)
            }
        } else {
            favouritesList.add(coin.id!!)
            (view as ShapeableImageView).setImageResource(R.drawable.ic_favourite)
        }
        return favouritesList
    }
}