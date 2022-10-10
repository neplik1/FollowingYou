package com.example.followingyou.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.followingyou.domain.model.NewsItem
import com.example.followingyou.domain.NewsListRepository
import kotlin.random.Random

object NewsListRepositoryImpl : NewsListRepository {

    private val newsListLD = MutableLiveData<List<NewsItem>>()
    private val newsList = sortedSetOf<NewsItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 1000) {
            val item = NewsItem("Name $i", i, Random.nextBoolean())
            addNewsItem(item)
        }
    }

    override fun getNewsList(): LiveData<List<NewsItem>> {
        return newsListLD
    }

    override fun addNewsItem(newsItem: NewsItem) {
        if (newsItem.id == NewsItem.UNDEFINED_ID) {
            newsItem.id = autoIncrementId++
        }
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