package com.seojongcheol.androidnews.common

import android.content.Context
import androidx.room.Room
import com.seojongcheol.androidnews.utils.room.AppDatabase
import com.seojongcheol.androidnews.utils.room.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "news.db").build()
    }

    @Provides
    fun provideNewsDao(database: AppDatabase): NewsDao = database.newsDao()
}