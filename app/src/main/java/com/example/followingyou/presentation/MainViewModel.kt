package com.example.followingyou.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.followingyou.data.NewsListRepositoryImpl
import com.example.followingyou.domain.DeleteNewsItemUseCase
import com.example.followingyou.domain.GetNewsListUseCase
import com.example.followingyou.domain.NewsItem

class MainViewModel : ViewModel() {
    private val repository = NewsListRepositoryImpl

    private val getNewsListUseCase = GetNewsListUseCase(repository)
    private val deleteNewsItemUseCase = DeleteNewsItemUseCase(repository)

    val newsList = MutableLiveData<List<NewsItem>>()

    fun getNewsList() {
        val list = getNewsListUseCase.getNewsList()
        newsList.value = list
    }
}