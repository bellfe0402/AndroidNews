package com.seojongcheol.androidnews.common

import com.seojongcheol.androidnews.network.ApiService
import com.seojongcheol.androidnews.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitService.apiService
    }
}