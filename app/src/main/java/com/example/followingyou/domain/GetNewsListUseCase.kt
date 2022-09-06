package com.example.followingyou.domain

import androidx.lifecycle.LiveData

class GetNewsListUseCase(private val newsListRepository: NewsListRepository) {

    fun getNewsList(): LiveData<List<NewsItem>> {
        return newsListRepository.getNewsList()
    }
}