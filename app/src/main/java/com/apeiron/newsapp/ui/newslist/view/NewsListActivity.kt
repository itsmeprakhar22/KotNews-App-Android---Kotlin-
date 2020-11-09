package com.apeiron.newsapp.ui.newslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apeiron.newsapp.R
import com.apeiron.newsapp.data.api.ApiHelperImp
import com.apeiron.newsapp.data.api.RetrofitBuilder
import com.apeiron.newsapp.data.model.Article
import com.apeiron.newsapp.data.model.News
import com.apeiron.newsapp.ui.base.ViewModelFactory
import com.apeiron.newsapp.ui.newslist.adapter.NewsListAdapter
import com.apeiron.newsapp.ui.newslist.viewmodel.NewsListViewModel
import com.apeiron.newsapp.utils.Status
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var adapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )

        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        newsListViewModel.getNews().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { newsList ->
                        renderList(newsList)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show();
                    recyclerView.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun renderList(newsList: List<Article>) {
        adapter.addData(newsList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        newsListViewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelperImp(RetrofitBuilder.apiService)))
                .get(NewsListViewModel::class.java)
    }
}