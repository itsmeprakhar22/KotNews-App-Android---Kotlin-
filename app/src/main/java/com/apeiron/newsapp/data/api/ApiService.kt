package com.apeiron.newsapp.data.api

import com.apeiron.newsapp.data.model.Article
import com.apeiron.newsapp.data.model.News
import retrofit2.Call
import retrofit2.http.GET

//Required for retrofit
interface ApiService {

    @GET("top-headlines?country=in&category=business&apiKey=fc24601cc6024b61947430a6116eba71")
    suspend fun getNews(): News

    @GET("more-news")
    suspend fun getMoreNews(): List<News>

}