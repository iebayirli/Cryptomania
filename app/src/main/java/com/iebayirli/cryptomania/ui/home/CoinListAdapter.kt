package com.iebayirli.cryptomania.ui.home

import com.iebayirli.cryptomania.BR
import com.iebayirli.cryptomania.R
import com.iebayirli.cryptomania.base.BaseListAdapter
import com.iebayirli.cryptomania.databinding.ItemCoinBinding
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.service.listeners.IFavouriteSelectListener
import com.iebayirli.cryptomania.service.listeners.IItemSelectListener

class CoinListAdapter(
        private val coinClickListener: IItemSelectListener,
        private val favoriteClickListener: IFavouriteSelectListener
) : BaseListAdapter<ItemCoinBinding, Coin>() {

    override val layoutRes: Int = R.layout.item_coin

    override fun bind(binding: ItemCoinBinding, item: Coin) {
        binding.apply {
            coin = item
            listener = coinClickListener
            favouriteSelectListener = favoriteClickListener
            executePendingBindings()
        }
    }


}