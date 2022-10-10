package com.example.followingyou.domain.useCase

import com.example.followingyou.domain.model.NewsItem
import com.example.followingyou.domain.NewsListRepository

class GetNewsItemUseCase(private val newsListRepository: NewsListRepository) {

    fun getNewsItem(newsItemId: Int): NewsItem {
        return newsListRepository.getNewsItem(newsItemId)
    }
}