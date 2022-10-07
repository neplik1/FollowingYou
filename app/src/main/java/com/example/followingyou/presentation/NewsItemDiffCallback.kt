package com.example.followingyou.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.followingyou.domain.NewsItem

class NewsItemDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}