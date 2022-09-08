package com.example.followingyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.followingyou.databinding.FragmentChangePasswordBinding
import com.example.followingyou.databinding.FragmentLogInBinding

class ChangePasswordFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        launchChangeMode()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchChangeMode() {
        binding.btnChangePassword.setOnClickListener {
            Toast.makeText(context, "Password changed", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val NAME = "ChangePasswordBindingFragment"

        fun newInstance(): ChangePasswordFragment {
            return ChangePasswordFragment()
        }
    }
}