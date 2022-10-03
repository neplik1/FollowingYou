package com.example.followingyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R
import com.example.followingyou.databinding.FragmentSingUpBinding

class SingUpFragment : Fragment() {
    private lateinit var viewModel: SingupViewModel

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
        viewModel = ViewModelProvider(this)[SingupViewModel::class.java]
        viewModel.isLoginFieldCorrect.observe(viewLifecycleOwner) { correct ->
            binding.etEmail.error = if (correct) {
                null
            } else {
                getString(R.string.error_email_text)
            }
        }
        viewModel.isPasswordFieldCorrect.observe(viewLifecycleOwner) { correct ->
            binding.etPassword.error = if (correct) {
                null
            } else {
                getString(R.string.error_password_text)
            }
        }
        viewModel.isPasswordConfirmFieldCorrect.observe(viewLifecycleOwner) { correct ->
            binding.etConfirmPassword.error = if (correct) {
                null
            } else {
                getString(R.string.error_confirmPassword_text)
            }
        }

        viewModel.isAuthorized.observe(viewLifecycleOwner) { authorized ->
            if (authorized) {
                viewModel.authorizedStatusProcessed()
            }
        }
        with(binding) {
            etEmail.addTextChangedListener {
                viewModel.resetErrors()
            }
            etPassword.addTextChangedListener {
                viewModel.resetErrors()
            }
            etConfirmPassword.addTextChangedListener {
                viewModel.resetErrors()
            }
            toggleLogInButton.setOnClickListener {
                launchChooseLogInFragment()
            }
            forgotPassword.setOnClickListener {
                launchChangePasswordFragment()
            }
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
            authorize()
            Toast.makeText(context, "Sing Up", Toast.LENGTH_SHORT).show()
        }
    }

    private fun authorize() {
        val login = binding.etEmail.text?.toString() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""
        val confirmPassword = binding.etConfirmPassword.text?.toString() ?: ""
        viewModel.authorize(login, password, confirmPassword)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}