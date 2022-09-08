package com.example.followingyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.followingyou.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private var isLoginModeActive = true

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

//    private var screenMode: String = MODE_UNKNOW

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
//        launchRightMode()
    }


//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_SIGN_UP -> launchSignUpMode()
//            MODE_LOG_IN -> launchLogInMode()
//            MODE_PASSWORD_CHANGE -> launchPasswordChange()
//        }
//    }
//
//    private fun launchSignUpMode() {
//        binding.btnChangePassword.visibility = View.GONE
//        binding.etNewPassword.visibility = View.GONE
//    }
//
//    private fun launchLogInMode() {
//        binding.btnChangePassword.visibility = View.GONE
//        binding.etNewPassword.visibility = View.GONE
//        binding.etEmail.visibility = View.GONE
//        binding.etConfirmPassword.visibility = View.GONE
//        binding.logInSignUpButton.text = "Log In"
//
//    }
//
//    private fun launchPasswordChange() {
//        binding.etName.visibility = View.GONE
//        binding.etPassword.visibility = View.GONE
//        binding.logInSignUpButton.visibility = View.GONE
//        binding.toggleLoginSignUpTextView.visibility = View.GONE
//
//    }

//    fun  toggleLoginSignUp() {
//        if (isLoginModeActive) {
//            isLoginModeActive = false
//            binding.logInSignUpButton.setText("Sign Up")
//            binding.toggleLoginSignUpTextView.setText("Or log in")
//            binding.etConfirmPassword.setVisibility(View.VISIBLE)
//        } else {
//            isLoginModeActive = true
//            binding.logInSignUpButton.setText("Log In")
//            binding.toggleLoginSignUpTextView.setText("Or sign up")
//           binding.etConfirmPassword.setVisibility(View.GONE)
//        }
//    }
//
//    companion object {
//        private const val EXTRA_SCREEN_MODE = "extra_mode"
//        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
//        private const val MODE_LOG_IN = "mode_logIn"
//        private const val MODE_SIGN_UP = "mode_signUp"
//        private const val MODE_PASSWORD_CHANGE = "mode_passwordChange"
//        private const val MODE_UNKNOW = ""
//    }
}