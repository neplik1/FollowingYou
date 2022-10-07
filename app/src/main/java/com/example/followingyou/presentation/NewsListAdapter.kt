package com.example.followingyou.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.followingyou.R
import com.example.followingyou.domain.NewsItem

class NewsListAdapter: ListAdapter<NewsItem, NewsItemViewHolder>(NewsItemDiffCallback()) {

    var onNewsItemClickListener: ((NewsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news,
            parent,
            false
        )
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.tvName.text = newsItem.name
        holder.view.setOnLongClickListener {
            true
        }
        holder.view.setOnClickListener {
            onNewsItemClickListener?.invoke(newsItem)
        }
        holder.tvName.text = newsItem.name
    }
}