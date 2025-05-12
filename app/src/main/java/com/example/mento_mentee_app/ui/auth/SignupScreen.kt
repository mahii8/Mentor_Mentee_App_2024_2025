package com.example.mento_mentee_app.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController) {
    val signupViewModel: SignupViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var skill by remember { mutableStateOf("") }

    val skillsList = listOf("Frontend", "Backend", "Fullstack", "DevOps", "Design")

    val context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val isLoading = signupViewModel.isLoading
    val errorMessage = signupViewModel.errorMessage
    val successMessage = signupViewModel.successMessage

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

        Column(
            modifier = Modifier
                .background(Color(0xFFFFF1F1), shape = RoundedCornerShape(8.dp))
                .padding(14.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
                Icon(Icons.Default.Person, contentDescription = "Icon", tint = Color(0xFF3F2C2C), modifier = Modifier.size(28.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Signup", color = Color(0xFF3F2C2C), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

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

            Spacer(modifier = Modifier.height(8.dp))

            Text("Select Skill", color = Color(0xFF3F2C2C))
            SkillDropdownMenu(
                selectedSkill = skill,
                skillsList = skillsList,
                onSkillSelected = { skill = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    when {
                        name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_LONG).show()
                        }
                        password != confirmPassword -> {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                        }
                        role.isBlank() -> {
                            Toast.makeText(context, "Please select a role", Toast.LENGTH_LONG).show()
                        }
                        skill.isBlank() -> {
                            Toast.makeText(context, "Please select a skill", Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            signupViewModel.signup(name, email, password, confirmPassword, role, skill) {
                                navController.navigate(if (role == "mentor") "mentorHome" else "menteeHome")
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F2C2C),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isLoading.value) "Signing Up..." else "Sign Up")
            }

            Spacer(modifier = Modifier.height(12.dp))

            successMessage.value?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
            errorMessage.value?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

            Text(
                text = "Already have an account? Login",
                color = Color(0xFF3F2C2C),
                modifier = Modifier.clickable { navController.navigate("login") }
            )
        }
    }
}

@Composable
fun SkillDropdownMenu(
    selectedSkill: String?,
    skillsList: List<String>,
    onSkillSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedSkill ?: "",
            onValueChange = {},
            label = { Text("Skill") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            skillsList.forEach { skill ->
                DropdownMenuItem(
                    text = { Text(skill) },
                    onClick = {
                        onSkillSelected(skill)
                        expanded = false
                    }
                )
            }
        }
    }
}
