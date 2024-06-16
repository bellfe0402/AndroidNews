package com.seojongcheol.androidnews.list

import androidx.lifecycle.LiveData
import com.seojongcheol.androidnews.network.ApiService
import com.seojongcheol.androidnews.utils.room.dao.NewsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    val news: LiveData<List<NewsItem>> = newsDao.getNews()
    private suspend fun insertAll(newsItem: List<NewsItem>) {
        newsDao.insertAll(newsItem)
    }

    private suspend fun deleteAll() {
        newsDao.deleteAll()
    }

    suspend fun fetchNews() {
        try {
            val response = apiService.getNews("kr", "abeace9d149045879d432af894599239")
            if (response.isSuccessful) {
                response.body()?.articles?.let {
                    deleteAll()
                    insertAll(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}