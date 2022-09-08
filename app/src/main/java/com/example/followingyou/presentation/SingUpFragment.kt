package com.example.followingyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R
import com.example.followingyou.databinding.FragmentSingUpBinding

class SingUpFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.toggleLogInButton.setOnClickListener {
            launchChooseLogInFragment()
        }
        binding.forgotPassword.setOnClickListener {
            launchChangePasswordFragment()
        }
        launchAddMode()
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

    private fun launchAddMode() {
        binding.signUpButton.setOnClickListener {
          Toast.makeText(context,"Sing Up",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}