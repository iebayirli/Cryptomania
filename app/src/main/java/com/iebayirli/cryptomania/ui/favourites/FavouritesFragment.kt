package com.iebayirli.cryptomania.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.iebayirli.cryptomania.R
import com.iebayirli.cryptomania.adapter.CommonRecyclerViewAdapter
import com.iebayirli.cryptomania.base.BaseFragment
import com.iebayirli.cryptomania.databinding.FragmentFavouritesBinding
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.service.listeners.IFavouriteSelectListener
import com.iebayirli.cryptomania.service.listeners.IItemSelectListener
import com.iebayirli.cryptomania.ui.home.HomeFragmentDirections
import com.iebayirli.cryptomania.ui.main.MainViewModel
import com.iebayirli.cryptomania.utils.Utils
import com.iebayirli.cryptomania.utils.observeNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding, FavouritesViewModel>(),
    IItemSelectListener, IFavouriteSelectListener {

    override val layoutId: Int = R.layout.fragment_favourites

    override val viewModel: FavouritesViewModel by viewModels()

    private val mainViewModel: MainViewModel by viewModels()

    private val rvAdapter = CommonRecyclerViewAdapter<Coin>(
        R.layout.item_coin,
        listOf(),
        this,
        this
    )

    override fun onReady(savedInstanceState: Bundle?) {
        binding.rvCoinList.adapter = rvAdapter

        viewModel.coinList.observeNotNull(this, {
            rvAdapter.updateData(it)
        })

        mainViewModel.favouritesList.observeNotNull(this, {
            viewModel.getCoins(it)
        })
    }

    override fun itemSelected(coin: Coin) {
        navController.navigate(
            HomeFragmentDirections.actionGotoDetailFragment(coin)
        )
    }

    override fun favouriteAdded(view: View, coin: Coin) {
        val favouritesList = mutableSetOf<String>()
        favouritesList.addAll(mainViewModel.favouritesList.value ?: mutableSetOf())
        mainViewModel.updateFavouriteList(Utils.setFavoriteClickedUI(favouritesList, view, coin))
    }

}