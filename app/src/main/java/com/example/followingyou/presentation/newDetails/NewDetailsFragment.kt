package com.example.followingyou.presentation.newDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R
import com.example.followingyou.databinding.FragmentNewDetailsBinding
import com.example.followingyou.databinding.FragmentNewsListBinding

class NewDetailsFragment : Fragment() {
    private lateinit var viewModel: NewDetailsViewModel

    private var _binding: FragmentNewDetailsBinding? = null
    private val binding: FragmentNewDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewDetailsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewDetailsBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}