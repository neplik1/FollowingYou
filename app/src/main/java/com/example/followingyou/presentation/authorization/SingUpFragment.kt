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
import com.example.followingyou.databinding.FragmentSingUpBinding
import com.example.followingyou.presentation.NewsListFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SingUpFragment : Fragment() {
    private lateinit var viewModel: SingupViewModel
    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentSingUpBinding? = null
    private val binding: FragmentSingUpBinding
        get() = _binding ?: throw RuntimeException("FragmentSingUpBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
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

    private fun launchSuccessSingUp() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment.newInstance())
            .addToBackStack(NewsListFragment.NAME)
            .commit()
    }

    private fun launchAddMode() {
        binding.signUpButton.setOnClickListener {
            authorize()
            if (binding.etEmail.text.isNullOrEmpty() || binding.etPassword.text!!.length <= 5
                || binding.etConfirmPassword.text.isNullOrEmpty()
            ) {

                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(binding.etEmail.text.toString()
                    .trim { it <= ' ' },
                    binding.etPassword.text.toString().trim { it <= ' ' })
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(
                                TAG, "createUserWithEmail:success"
                            )
                            Toast.makeText(context, "Sing Up", Toast.LENGTH_SHORT).show()
                            launchSuccessSingUp()
                            val user = auth.currentUser
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(
                                TAG, "createUserWithEmail:failure",
                                task.exception
                            )
                            Toast.makeText(
                                context, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            //updateUI(null);
                        }
                    }
            }
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

    companion object {
        private const val TAG = "SingUpFragment"
    }
}