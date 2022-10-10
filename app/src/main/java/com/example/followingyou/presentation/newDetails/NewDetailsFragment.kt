package com.example.followingyou.presentation.newDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R

class NewDetailsFragment : Fragment() {
    private lateinit var viewModel: NewDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewDetailsViewModel::class.java]
    }

    companion object {
        const val NAME = "NewDetailsFragment"
        fun newInstance(): NewDetailsFragment {
            return NewDetailsFragment()
        }
    }
}