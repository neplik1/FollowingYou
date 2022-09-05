package com.example.followingyou.domain

class GetNewsItemUseCase(private val newsListRepository: NewsListRepository) {

    fun getNewsItem(newsItemId: Int): NewsItem {
        return newsListRepository.getNewsItem(newsItemId)
    }
}