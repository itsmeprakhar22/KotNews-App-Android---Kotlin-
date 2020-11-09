package com.apeiron.newsapp.data.repository

import com.apeiron.newsapp.data.model.News

interface NewsListRepository {
    suspend fun getNews(): News
    suspend fun getMoreNews(): List<News>
}