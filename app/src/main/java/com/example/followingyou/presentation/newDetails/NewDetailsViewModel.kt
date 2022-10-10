package com.example.followingyou.presentation.newDetails

import androidx.lifecycle.ViewModel
import com.example.followingyou.data.NewsListRepositoryImpl
import com.example.followingyou.data.NewsListRepositoryImpl.getNewsItem
import com.example.followingyou.domain.useCase.GetNewsItemUseCase

class NewDetailsViewModel:ViewModel() {

    private val repository = NewsListRepositoryImpl

    private val getNewsItemUseCase = GetNewsItemUseCase(repository)

    fun getNewsItem(newsItemId: Int) {
        val item = getNewsItemUseCase.getNewsItem(newsItemId)
    }

}