package com.apeiron.newsapp.ui.newslist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apeiron.newsapp.data.model.Article
import com.apeiron.newsapp.data.model.News
import com.apeiron.newsapp.data.repository.NewsListRepository
import com.apeiron.newsapp.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import javax.security.auth.callback.Callback

private const val TAG = "NewsListViewModel"

class NewsListViewModel(private val newsListRepository: NewsListRepository) : ViewModel() {

    private val newsList = MutableLiveData<Resource<List<Article>>>()

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            //To show loading
            newsList.postValue((Resource.loading(data = null)))

            try {
                //NETWORK CALL SERIES
                /**
                val response = newsListRepository.getNews()
                val moreResponse = newsListRepository.getNews()
                Log.d(TAG, "fetchNews: " + response)
                val allResponse = mutableListOf<Article>()
                allResponse.addAll(response.articles)
                allResponse.addAll(moreResponse.articles)
                */
//                allResponse.addAll(moreResponse)
                //NETWORK PARALLEL
                val responseDeferred = async { newsListRepository.getNews() }
                val moreResponseDeferred = async { newsListRepository.getNews() }

                val response = responseDeferred.await()
                Log.d(TAG, "fetchNews: R " + response.articles)
                Log.d(TAG, "WAIT")
                val moreResponse = moreResponseDeferred.await()
                Log.d(TAG, "fetchNews: MR " + moreResponse.articles)

                val allResponse = mutableListOf<Article>()
                allResponse.addAll(response.articles)
                allResponse.addAll(moreResponse.articles)
                newsList.postValue(Resource.success(allResponse))

            } catch (e: Exception) {
                e.printStackTrace()
                newsList.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getNews(): LiveData<Resource<List<Article>>> {
        return newsList
    }
}