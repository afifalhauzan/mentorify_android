package com.example.mentorify.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    // In RegisterViewModel.kt
    val isFormValid: Boolean
        get() = name.isNotBlank() &&
                email.isNotBlank() &&
                phoneNumber.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword.isNotBlank() &&
                password == confirmPassword


    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var registrationMessage by mutableStateOf<String?>(null)
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        phoneNumber = newPhoneNumber
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
    }

    fun register() {
        // Basic validation and simulated registration
        if (name.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            registrationMessage = "Please fill all fields."
            return
        }
        if (password != confirmPassword) {
            registrationMessage = "Passwords do not match."
            return
        }
        // Simulate successful registration
        registrationMessage = "Registration successful!"
        // In a real app, you'd make an API call here
    }

    fun resetRegistrationMessage() {
        registrationMessage = null
    }
}