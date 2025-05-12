package com.example.mento_mentee_app.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(navController: NavController) {
    // Initialize the ViewModel
    val loginViewModel: LoginViewModel = viewModel()

    // State variables for input fields
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("mentor") } // Default role is "mentor"
    val context = LocalContext.current

    // State for toggling password visibility
    var passwordVisible by remember { mutableStateOf(false) }

    // Handle API response (success or failure)
    val isLoading = loginViewModel.isLoading
    val errorMessage = loginViewModel.errorMessage
    val successMessage = loginViewModel.successMessage

    // UI layout for the Login screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mentor-Mentee App",
            color = Color(0xFF3F2C2C),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Form Card
        Column(
            modifier = Modifier
                .background(Color(0xFFFFF1F1), shape = RoundedCornerShape(8.dp))
                .padding(14.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Login,
                    contentDescription = "Login Icon",
                    tint = Color(0xFF3F2C2C),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Login", color = Color(0xFF3F2C2C), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            // Email Input
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email", color = Color(0xFF3F2C2C)) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            // Password Input with Toggle Visibility
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color(0xFF3F2C2C)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Role Selection
            Text("Select Role", color = Color(0xFF3F2C2C))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(selected = role == "mentor", onClick = { role = "mentor" })
                Text("Mentor", color = Color(0xFF3F2C2C))
                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(selected = role == "mentee", onClick = { role = "mentee" })
                Text("Mentee", color = Color(0xFF3F2C2C))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    // Call login in ViewModel with the role and pass the context
                    loginViewModel.login(email, password, role, context) {
                        when (role) {
                            "mentor" -> navController.navigate("mentorHome")
                            "mentee" -> navController.navigate("menteeHome")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F2C2C),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isLoading.value) "Logging In..." else "Login")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Show messages (error or success)
            successMessage.value?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
            errorMessage.value?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

            // No account? Signup
            Text(
                text = "Don't have an account? Sign Up",
                color = Color(0xFF3F2C2C),
                modifier = Modifier.clickable {
                    navController.navigate("signup")
                }
            )
        }
    }
}
