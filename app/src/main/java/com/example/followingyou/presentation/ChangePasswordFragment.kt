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
import com.example.followingyou.databinding.FragmentChangePasswordBinding


class ChangePasswordFragment : Fragment() {
    private lateinit var viewModel: SingupViewModel

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding: FragmentChangePasswordBinding
        get() = _binding ?: throw RuntimeException("FragmentChangePasswordBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
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
            binding.etNewPassword.error = if (correct) {
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
            etNewPassword.addTextChangedListener {
                viewModel.resetErrors()
            }
            etConfirmPassword.addTextChangedListener {
                viewModel.resetErrors()
            }
        }
        launchChangeMode()
    }

    private fun launchChangeMode() {
        binding.btnChangePassword.setOnClickListener {
            authorize()
            Toast.makeText(context, "Password changed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun authorize() {
        val login = binding.etEmail.text?.toString() ?: ""
        val password = binding.etNewPassword.text?.toString() ?: ""
        val confirmPassword = binding.etConfirmPassword.text?.toString() ?: ""
        viewModel.authorize(login, password, confirmPassword)
    }

    companion object {
        const val NAME = "ChangePasswordBindingFragment"

        fun newInstance(): ChangePasswordFragment {
            return ChangePasswordFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}