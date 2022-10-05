package com.example.followingyou.domain.useCase

import androidx.lifecycle.LiveData
import com.example.followingyou.domain.NewsItem
import com.example.followingyou.domain.NewsListRepository

class GetNewsListUseCase(private val newsListRepository: NewsListRepository) {

    fun getNewsList(): LiveData<List<NewsItem>> {
        return newsListRepository.getNewsList()
    }
}