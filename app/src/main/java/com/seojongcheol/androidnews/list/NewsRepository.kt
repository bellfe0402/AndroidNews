package com.seojongcheol.androidnews.list

import com.seojongcheol.androidnews.network.ApiService
import com.seojongcheol.androidnews.network.NewsResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getTopHeadlines(): Response<NewsResponse> = apiService.getNews("kr", "abeace9d149045879d432af894599239")
}