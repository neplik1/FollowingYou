package com.example.followingyou.presentation.newsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.followingyou.R
import com.example.followingyou.presentation.SelectedNewsFragment
import com.example.followingyou.presentation.newsList.adapter.NewsListAdapter

class NewsListFragment : Fragment() {
    private lateinit var viewModel: NewsListViewModel
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
        viewModel = ViewModelProvider(this)[NewsListViewModel::class.java]
        viewModel.newsList.observe(viewLifecycleOwner) {
            newsListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView(view: View) {
        val rvShopList = view.findViewById<RecyclerView>(R.id.rv_shop_list)
        newsListAdapter = NewsListAdapter()
        rvShopList.adapter = newsListAdapter

        setupClickListener()
        setupSwipeListener(rvShopList)
    }

    private fun setupClickListener() {
        newsListAdapter.onNewsItemClickListener = {
            launchSelectedNewsFragment()
        }
    }

    private fun setupSwipeListener(rvNewsList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = newsListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNewsItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvNewsList)
    }

    private fun launchSelectedNewsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SelectedNewsFragment.newInstance())
            .addToBackStack(SelectedNewsFragment.NAME)
            .commit()
    }

    companion object {
        const val NAME = "LogInFragment"
        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }
}
