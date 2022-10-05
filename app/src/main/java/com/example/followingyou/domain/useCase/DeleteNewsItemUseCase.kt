package com.example.followingyou.domain.useCase

import com.example.followingyou.domain.NewsItem
import com.example.followingyou.domain.NewsListRepository

class DeleteNewsItemUseCase(private val newsListRepository: NewsListRepository) {

    fun deleteNewsItem(newsItem: NewsItem) {
        newsListRepository.deleteNewsItem(newsItem)
    }
}