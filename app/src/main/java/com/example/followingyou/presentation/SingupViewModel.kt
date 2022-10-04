package com.example.followingyou.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingupViewModel : ViewModel() {

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean>
        get() = _isAuthorized

    private val _isLoginFieldCorrect = MutableLiveData<Boolean>()
    val isLoginFieldCorrect: LiveData<Boolean>
        get() = _isLoginFieldCorrect

    private val _isPasswordFieldCorrect = MutableLiveData<Boolean>()
    val isPasswordFieldCorrect: LiveData<Boolean>
        get() = _isPasswordFieldCorrect

    private val _isPasswordConfirmFieldCorrect = MutableLiveData<Boolean>()
    val isPasswordConfirmFieldCorrect: LiveData<Boolean>
        get() = _isPasswordConfirmFieldCorrect

    fun authorize(login: String, password: String, confirmPassword: String) {
        _isAuthorized.value = validateFields(login, password, confirmPassword)
    }

    fun resetErrors() {
        _isPasswordFieldCorrect.value = true
        _isLoginFieldCorrect.value = true
        _isPasswordConfirmFieldCorrect.value = true
    }

    fun authorizedStatusProcessed() {
        _isAuthorized.value = false
    }

     fun validateFields(login: String, password: String, confirmPassword: String): Boolean {
        // Just for testing
//        val loginValid = true
//        val passwordValid = true

        // Uncomment this for true validation
        val loginValid = login.trim().isNotEmpty()
        val passwordValid = password.trim().length >= MIN_LENGTH
        val passwordConfirmValid = confirmPassword.trim() == password.trim()

        _isLoginFieldCorrect.value = loginValid
        _isPasswordFieldCorrect.value = passwordValid
        _isPasswordConfirmFieldCorrect.value = passwordConfirmValid
        return loginValid && passwordValid && passwordConfirmValid
    }

    companion object {
        private const val MIN_LENGTH = 6
    }
}