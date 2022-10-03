package com.example.followingyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R
import com.example.followingyou.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var viewModel: SingupViewModel

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
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
            forgotPassword.setOnClickListener {
                launchChangePasswordFragment()
            }
        }
        launchAddMode()
    }

    private fun launchChangePasswordFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ChangePasswordFragment.newInstance())
            .addToBackStack(ChangePasswordFragment.NAME)
            .commit()
    }

    private fun launchAddMode() {
        binding.logInButton.setOnClickListener {
            authorize()
            Toast.makeText(context, "Log In", Toast.LENGTH_SHORT).show()
        }
    }

    private fun authorize() {
        val login = binding.etEmail.text?.toString() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""
        val confirmPassword = ""
        viewModel.authorize(login, password, confirmPassword)
    }

    companion object {
        const val NAME = "LogInFragment"
        fun newInstance(): LogInFragment {
            return LogInFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}