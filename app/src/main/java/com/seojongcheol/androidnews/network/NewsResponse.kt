package com.seojongcheol.androidnews.network

import com.seojongcheol.androidnews.list.NewsItem

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsItem>
)