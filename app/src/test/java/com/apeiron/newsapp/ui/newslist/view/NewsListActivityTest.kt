package com.apeiron.newsapp.ui.newslist.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.apeiron.newsapp.data.model.Article
import com.apeiron.newsapp.data.model.News
import com.apeiron.newsapp.data.repository.NewsListRepository
import com.apeiron.newsapp.ui.newslist.viewmodel.NewsListViewModel
import com.apeiron.newsapp.utils.Resource
import com.apeiron.newsapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsListActivityTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var newsListRepository: NewsListRepository

    @Mock
    private lateinit var newsListObserver: Observer<Resource<List<Article>>>

    @Before
    fun setUp() {

    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
//        testCoroutineRule.runBlockingTest {
//            doReturn(emptyList<Article>())
//                .`when`(newsListRepository)
//                .getNews()
//            val viewModel = NewsListViewModel(newsListRepository)
//            viewModel.getNews().observeForever(newsListObserver)
////            verify(newsListRepository).getNews()
//            verify(newsListObserver).onChanged(Resource.success(null))
////            viewModel.getNews().removeObserver(newsListObserver)
//        }
    }
}