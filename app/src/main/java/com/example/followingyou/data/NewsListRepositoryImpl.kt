package com.example.followingyou.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.followingyou.domain.NewsItem
import com.example.followingyou.domain.NewsListRepository

object NewsListRepositoryImpl : NewsListRepository {

    private val newsListLD = MutableLiveData<List<NewsItem>>()
    private val newsList = mutableListOf<NewsItem>()

    private var autoIncrementId = 0

    override fun getNewsList(): LiveData<List<NewsItem>> {
        return newsListLD
    }

    override fun addNewsItem(newsItem: NewsItem) {
        newsItem.id = autoIncrementId++
        newsList.add(newsItem)
        updateList()
    }

    override fun deleteNewsItem(newsItem: NewsItem) {
        newsList.remove(newsItem)
        updateList()
    }

    override fun getNewsItem(newsItemId: Int): NewsItem {
        return newsList.find { it.id == newsItemId } ?: throw RuntimeException("Element not found")
    }

    private fun updateList() {
        newsListLD.value = newsList.toList()
    }
}