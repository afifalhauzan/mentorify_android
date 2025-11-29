package com.example.mentorify.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginMessage by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun login() {
        // Simulate a login call
        if (email == "test@example.com" && password == "password") {
            loginMessage = "Login successful!"
            // In a real app, you'd navigate to the home screen here
        } else {
            loginMessage = "Invalid credentials."
        }
    }

    fun resetLoginMessage() {
        loginMessage = null
    }

    fun loginWithGoogle() {
        // Simulate Google login
        loginMessage = "Google login initiated."
    }
}