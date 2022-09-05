package com.example.followingyou.domain

class DeleteNewsItemUseCase(private val newsListRepository: NewsListRepository) {

    fun deleteNewsItem(newsItem: NewsItem) {
        newsListRepository.deleteNewsItem(newsItem)
    }
}