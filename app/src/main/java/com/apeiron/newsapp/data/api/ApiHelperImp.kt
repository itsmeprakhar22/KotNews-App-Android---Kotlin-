package com.apeiron.newsapp.data.api

import com.apeiron.newsapp.data.model.News

class ApiHelperImp(private val apiService: ApiService):ApiHelper {
    override suspend fun getNews() = apiService.getNews()

    override suspend fun getMoreNews() = apiService.getMoreNews()

}