package com.example.followingyou.domain

import androidx.lifecycle.LiveData

interface NewsListRepository {

    fun getNewsList(): LiveData<List<NewsItem>>
    fun addNewsItem(NewsItem: NewsItem)
    fun deleteNewsItem(NewsItem: NewsItem)
    fun getNewsItem(NewsItemId: Int): NewsItem

}