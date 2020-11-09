package com.apeiron.newsapp.data.api

import com.apeiron.newsapp.data.model.News
import retrofit2.Call

interface ApiHelper{
    suspend fun getNews(): News
    suspend fun getMoreNews(): List<News>
}