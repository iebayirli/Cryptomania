package com.iebayirli.cryptomania.di

import com.iebayirli.cryptomania.repository.CoinRepository
import com.iebayirli.cryptomania.service.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(coinService: CoinService) = CoinRepository(coinService)
}