package com.example.followingyou.presentation

import androidx.lifecycle.ViewModel
import com.example.followingyou.data.NewsListRepositoryImpl
import com.example.followingyou.domain.useCase.DeleteNewsItemUseCase
import com.example.followingyou.domain.useCase.GetNewsListUseCase
import com.example.followingyou.domain.model.NewsItem

class NewsListViewModel : ViewModel() {
    private val repository = NewsListRepositoryImpl

    private val getNewsListUseCase = GetNewsListUseCase(repository)
    private val deleteNewsItemUseCase = DeleteNewsItemUseCase(repository)

    val newsList = getNewsListUseCase.getNewsList()

    fun deleteNewsItem(newsItem: NewsItem) {
        deleteNewsItemUseCase.deleteNewsItem(newsItem)
    }
}