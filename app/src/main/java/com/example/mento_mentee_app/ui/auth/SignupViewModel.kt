package com.example.mento_mentee_app.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mento_mentee_app.data.api.SignupApi
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    var successMessage = mutableStateOf<String?>(null)

    fun signup(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        role: String,
        skill: String,
        onSuccess: () -> Unit
    ) {
        isLoading.value = true
        errorMessage.value = null
        successMessage.value = null

        if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() || role.isBlank() || skill.isBlank()) {
            errorMessage.value = "All fields are required."
            isLoading.value = false
            return
        }

        if (password != confirmPassword) {
            errorMessage.value = "Passwords do not match."
            isLoading.value = false
            return
        }

        viewModelScope.launch {
            try {
                val result = SignupApi.signup(name, email, password, role, skill)
                if (result.isSuccess) {
                    successMessage.value = "Signup successful!"
                    onSuccess()
                } else {
                    errorMessage.value = result.exceptionOrNull()?.message ?: "Signup failed."
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Unexpected error"
            } finally {
                isLoading.value = false
            }
        }
    }
}

