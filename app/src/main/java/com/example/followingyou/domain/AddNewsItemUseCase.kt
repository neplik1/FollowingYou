package com.example.followingyou.domain

class AddNewsItemUseCase(private val newsListRepository: NewsListRepository) {

    fun addNewsItem(newsItem: NewsItem) {
        newsListRepository.addNewsItem(newsItem)
    }
}