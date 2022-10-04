package com.example.followingyou.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.followingyou.R

class NewsListFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.newsList.observe(viewLifecycleOwner) {
            newsListAdapter.newsList=it
        }
    }

    private fun setupRecyclerView(view: View) {
        val rvShopList = view.findViewById<RecyclerView>(R.id.rv_shop_list)
        newsListAdapter = NewsListAdapter()
        rvShopList.adapter = newsListAdapter
    }

    companion object {
        const val NAME = "LogInFragment"
        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }
}
