package com.example.followingyou.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R
import com.example.followingyou.presentation.authorization.LogInFragment

class SelectedNewsFragment : Fragment() {
    private lateinit var viewModel: SelectedNewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SelectedNewsViewModel::class.java]
    }

    companion object {
        const val NAME = "SelectedNewsFragment"
        fun newInstance(): SelectedNewsFragment {
            return SelectedNewsFragment()
        }
    }
}