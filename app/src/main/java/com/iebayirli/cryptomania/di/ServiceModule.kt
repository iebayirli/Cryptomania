package com.iebayirli.cryptomania.di

import com.iebayirli.cryptomania.service.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCoinService(retrofit: Retrofit): CoinService = retrofit.create(CoinService::class.java)

}