package com.example.followingyou.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.followingyou.R
import com.example.followingyou.domain.NewsItem

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    var newsList = listOf<NewsItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        val newsItem = this.newsList[position]
        holder.tvName.text = newsItem.name
        holder.view.setOnLongClickListener {
            true
        }
        holder.view.setOnClickListener {
            onNewsItemClickListener?.invoke(newsItem)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
    }
}