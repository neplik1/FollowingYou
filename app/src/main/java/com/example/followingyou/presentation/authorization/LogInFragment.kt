package com.example.followingyou.presentation.authorization

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.R
import com.example.followingyou.databinding.FragmentLogInBinding
import com.example.followingyou.presentation.newsList.NewsListFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
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

    private fun launchSuccessLogIn() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment.newInstance())
            .addToBackStack(NewsListFragment.NAME)
            .commit()
    }

    private fun launchAddMode() {
        binding.logInButton.setOnClickListener {
            authorize()
            if (binding.etEmail.text.isNullOrEmpty() || binding.etPassword.text!!.length <= 5) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(binding.etEmail.text.toString().trim { it <= ' ' },
                    binding.etPassword.text.toString().trim { it <= ' ' })
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(NAME, "signInWithEmail:success")
                            launchSuccessLogIn()
                            val user = auth.currentUser
                            Toast.makeText(context, "Log In", Toast.LENGTH_SHORT).show()
                            // updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(NAME, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // updateUI(null)
                        }
                    }
            }
        }
    }

    private fun authorize() {
        val login = binding.etEmail.text?.toString() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""
        viewModel.authorize(login, password)
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