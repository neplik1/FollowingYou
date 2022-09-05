package com.example.followingyou.domain

interface NewsListRepository {

    fun getNewsList(): List<NewsItem>
    fun addNewsItem(NewsItem: NewsItem)
    fun deleteNewsItem(NewsItem: NewsItem)
    fun getNewsItem(NewsItemId: Int): NewsItem

}