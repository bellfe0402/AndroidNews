package com.seojongcheol.androidnews.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private const val baseUrl = "https://newsapi.org/v2/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)
    }
}