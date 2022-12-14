package com.example.followingyou.presentation.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean>
        get() = _isAuthorized

    private val _isLoginFieldCorrect = MutableLiveData<Boolean>()
    val isLoginFieldCorrect: LiveData<Boolean>
        get() = _isLoginFieldCorrect

    private val _isPasswordFieldCorrect = MutableLiveData<Boolean>()
    val isPasswordFieldCorrect: LiveData<Boolean>
        get() = _isPasswordFieldCorrect

    fun authorize(login: String, password: String) {
        _isAuthorized.value = validateFields(login, password)
    }

    fun resetErrors() {
        _isPasswordFieldCorrect.value = true
        _isLoginFieldCorrect.value = true
    }

    fun authorizedStatusProcessed() {
        _isAuthorized.value = false
    }

    fun validateFields(login: String, password: String): Boolean {
        // Just for testing
//        val loginValid = true
//        val passwordValid = true

         //Uncomment this for true validation
        val loginValid = login.trim().isNotEmpty()
        val passwordValid = password.trim().length >= MIN_LENGTH

        _isLoginFieldCorrect.value = loginValid
        _isPasswordFieldCorrect.value = passwordValid
        return loginValid && passwordValid
    }

    companion object {
        private const val MIN_LENGTH = 6
    }
}