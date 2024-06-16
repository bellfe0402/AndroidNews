package com.seojongcheol.androidnews.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val news: LiveData<List<NewsItem>> = newsRepository.news
    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsRepository.fetchNews()
        }
    }
}