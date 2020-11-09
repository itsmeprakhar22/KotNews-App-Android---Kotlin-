package com.apeiron.newsapp.data.repository

import com.apeiron.newsapp.data.api.ApiHelper
import com.apeiron.newsapp.data.model.News

class NewsListRepositoryImpl(private val apiHelper: ApiHelper) : NewsListRepository {
    override suspend fun getNews() = apiHelper.getNews()

    override suspend fun getMoreNews() = apiHelper.getMoreNews()

}