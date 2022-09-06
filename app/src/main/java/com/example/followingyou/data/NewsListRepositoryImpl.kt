package com.example.followingyou.data

import com.example.followingyou.domain.NewsItem
import com.example.followingyou.domain.NewsListRepository

object NewsListRepositoryImpl : NewsListRepository {

    private val newsList = mutableListOf<NewsItem>()

    private var autoIncrementId = 0

    override fun getNewsList(): List<NewsItem> {
        return newsList.toList()
    }

    override fun addNewsItem(newsItem: NewsItem) {
        newsItem.id = autoIncrementId++
        newsList.add(newsItem)
    }

    override fun deleteNewsItem(newsItem: NewsItem) {
        newsList.remove(newsItem)
    }

    override fun getNewsItem(newsItemId: Int): NewsItem {
        return newsList.find { it.id == newsItemId } ?: throw RuntimeException("Element not found")
    }
}