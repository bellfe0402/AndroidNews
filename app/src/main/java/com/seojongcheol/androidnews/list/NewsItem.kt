package com.seojongcheol.androidnews.list

data class NewsItem(
    val title: String,
    val description: String,
    val publishedAt: String,
    val urlToImage: String,
    val url: String
)