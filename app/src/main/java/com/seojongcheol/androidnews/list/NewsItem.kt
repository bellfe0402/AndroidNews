package com.seojongcheol.androidnews.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsItem(
    @PrimaryKey val title: String,
    val description: String?,
    val publishedAt: String,
    val urlToImage: String?,
    val url: String,
    var isVisited: Boolean = false
)