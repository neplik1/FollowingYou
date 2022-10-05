package com.example.followingyou.domain.useCase

import com.example.followingyou.domain.NewsItem
import com.example.followingyou.domain.NewsListRepository

class AddNewsItemUseCase(private val newsListRepository: NewsListRepository) {

    fun addNewsItem(newsItem: NewsItem) {
        newsListRepository.addNewsItem(newsItem)
    }
}