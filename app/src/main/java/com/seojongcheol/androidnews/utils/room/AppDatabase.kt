package com.seojongcheol.androidnews.utils.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seojongcheol.androidnews.list.NewsItem
import com.seojongcheol.androidnews.utils.room.dao.NewsDao

@Database(entities = [NewsItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}