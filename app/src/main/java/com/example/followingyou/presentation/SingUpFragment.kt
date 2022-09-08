package com.example.followingyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.followingyou.R
import com.example.followingyou.databinding.FragmentSingUpBinding

class SingUpFragment : Fragment() {
    private var _binding: FragmentSingUpBinding? = null
    private val binding: FragmentSingUpBinding
        get() = _binding ?: throw RuntimeException("FragmentSingUpBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toggleLogInButton.setOnClickListener {
            launchChooseLogInFragment()
        }
        binding.forgotPassword.setOnClickListener {
            launchChangePasswordFragment()
        }
    }

    private fun launchChooseLogInFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LogInFragment.newInstance())
            .addToBackStack(LogInFragment.NAME)
            .commit()
    }

    private fun launchChangePasswordFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ChangePasswordFragment.newInstance())
            .addToBackStack(ChangePasswordFragment.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}