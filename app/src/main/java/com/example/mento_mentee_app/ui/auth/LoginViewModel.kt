package com.example.mento_mentee_app.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mento_mentee_app.data.api.LoginApi
import com.example.mento_mentee_app.data.api.LoginResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import android.widget.Toast
import android.content.Context
import com.example.mento_mentee_app.data.datastore.TokenManager

class LoginViewModel : ViewModel() {

    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    var successMessage = mutableStateOf<String?>(null)

    // Function to handle login
    fun login(email: String, password: String, role: String, context: Context, onSuccess: () -> Unit) {
        isLoading.value = true
        errorMessage.value = null
        successMessage.value = null

        if (email.isBlank() || password.isBlank() || role.isBlank()) {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_LONG).show()
            isLoading.value = false
            return
        }

        viewModelScope.launch {
            try {
                val result = LoginApi.login(email, password, role)
                if (result.isSuccess) {
                    val response: LoginResponse = result.getOrNull()!!
                    TokenManager(context).saveToken(response.token) // ✅ Save token
                    successMessage.value = "Login successful!"
                    onSuccess()
                } else {
                    errorMessage.value = result.exceptionOrNull()?.message ?: "Login failed"
                    Toast.makeText(context, errorMessage.value, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Unexpected error"
                Toast.makeText(context, errorMessage.value, Toast.LENGTH_LONG).show()
            } finally {
                isLoading.value = false
            }
        }
    }
}
