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
import com.example.followingyou.databinding.FragmentChangePasswordBinding
import com.example.followingyou.databinding.FragmentNewsListBinding
import com.example.followingyou.presentation.newDetails.NewDetailsFragment
import com.example.followingyou.presentation.newsList.adapter.NewsListAdapter

class NewsListFragment : Fragment() {
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    private var _binding: FragmentNewsListBinding? = null
    private val binding: FragmentNewsListBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
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
        val rvShopList = binding.rvShopList
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
            .replace(R.id.fragment_container, NewDetailsFragment.newInstance())
            .addToBackStack(NewDetailsFragment.NAME)
            .commit()
    }

    companion object {
        const val NAME = "LogInFragment"
        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
