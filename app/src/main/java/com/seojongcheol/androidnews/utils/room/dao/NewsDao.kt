package com.seojongcheol.androidnews.utils.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seojongcheol.androidnews.list.NewsItem

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getNews(): LiveData<List<NewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsItem: List<NewsItem>)

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}