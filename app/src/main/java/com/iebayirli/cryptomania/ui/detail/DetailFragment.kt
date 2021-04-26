package com.iebayirli.cryptomania.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.iebayirli.cryptomania.R
import com.iebayirli.cryptomania.adapter.CommonRecyclerViewAdapter
import com.iebayirli.cryptomania.base.BaseFragment
import com.iebayirli.cryptomania.databinding.FragmentDetailBinding
import com.iebayirli.cryptomania.model.Coin
import com.iebayirli.cryptomania.model.CoinDetail
import com.iebayirli.cryptomania.model.TimeInterval
import com.iebayirli.cryptomania.service.listeners.IFavouriteSelectListener
import com.iebayirli.cryptomania.service.listeners.ITimeIntervalSelectListener
import com.iebayirli.cryptomania.ui.main.MainViewModel
import com.iebayirli.cryptomania.utils.ChartHelper
import com.iebayirli.cryptomania.utils.Utils
import com.iebayirli.cryptomania.utils.observeNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(),
        IFavouriteSelectListener, ITimeIntervalSelectListener {

    override val layoutId: Int = R.layout.fragment_detail

    override val viewModel: DetailViewModel by viewModels()

    private val mainViewModel: MainViewModel by viewModels()

    private val favouritesList = mutableSetOf<String>()

    lateinit var coin: Coin

    private val timeIntervalAdapter = CommonRecyclerViewAdapter<TimeInterval>(R.layout.item_time_interval, listOf(), this)

    override fun onReady(savedInstanceState: Bundle?) {
        coin = arguments?.getSerializable("coin") as Coin

        coin.id?.let { viewModel.setupUI(it) }

        viewModel.coinDetail.observeNotNull(this, {
            setCoinDetailUI(it)
        })

        viewModel.coinHistory.observeNotNull(this, {
            ChartHelper.displayHistoricalLineChart(binding.chartLayout, coin.id!!, it.prices)
        })

        mainViewModel.favouritesList.observeNotNull(this, {
            favouritesList.clear()
            favouritesList.addAll(it)
        })

        binding.rvTimeInterval.adapter = timeIntervalAdapter

        viewModel.timeInterval.observeNotNull(this, {
            timeIntervalAdapter.updateData(it)
        })

    }

    private fun setCoinDetailUI(coinDetail: CoinDetail) {
        binding.tvHashingAlg.text = coinDetail.hashingAlgorithm
        binding.tvDesc.text = coinDetail.description?.desc
        binding.cardLayout.apply {
            data = coin
            favouriteSelectListener = this@DetailFragment
        }
    }

    override fun favouriteAdded(view: View, coin: Coin) {
        mainViewModel.updateFavouriteList(Utils.setFavoriteClickedUI(favouritesList, view, coin))
    }

    override fun timeIntervalChanged(timeInterval: TimeInterval) {
        viewModel.updateTimeInterval(timeInterval)
        viewModel.getCoinHistory(coin.id!!, timeInterval.timeZone)
        binding.tvChartTitle.text = timeInterval.title
    }

    private companion object {
        const val TAG = "DetailFragment"
    }

}