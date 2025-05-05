package com.example.mento_mentee_app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Mentor") }
    var skills by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val skillOptions = listOf(
        "Javascript", "Python", "Android Development", "iOS Development",
        "Web Development", "Data Science", "Machine Learning",
        "Cloud Computing", "DevOps"
    )

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

        // Signup Form Container
        Column(
            modifier = Modifier
                .background(Color(0xFFFFF1F1), shape = RoundedCornerShape(8.dp))
                .padding(14.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon and "Signup" title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Mentor-Mentee Icon",
                    tint = Color(0xFF3F2C2C),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Signup",
                    color = Color(0xFF3F2C2C),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name", color = Color(0xFF3F2C2C)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color(0xFF3F2C2C)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color(0xFF3F2C2C)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password", color = Color(0xFF3F2C2C)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Skill Dropdown
            Text(text = "Select Skill", color = Color(0xFF3F2C2C))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1F1))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (skills.isEmpty()) "Select a skill" else skills,
                            color = Color(0xFF3F2C2C),
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Icon",
                            tint = Color(0xFF3F2C2C)
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    skillOptions.forEach { skill ->
                        DropdownMenuItem(
                            onClick = {
                                skills = skill
                                expanded = false
                            },
                            text = {
                                Text(
                                    text = skill,
                                    color = if (skills == skill) Color(0xFF3F2C2C) else Color.Black,
                                    fontWeight = if (skills == skill) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Role Selection
            Text(text = "Select Role", color = Color(0xFF3F2C2C))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = role == "Mentor",
                    onClick = { role = "Mentor" }
                )
                Text(text = "Mentor", color = Color(0xFF3F2C2C))
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = role == "Mentee",
                    onClick = { role = "Mentee" }
                )
                Text(text = "Mentee", color = Color(0xFF3F2C2C))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Signup Button
            Button(
                onClick = {
                    if (role == "Mentor") {
                        navController.navigate("mentorHome")
                    } else {
                        navController.navigate("menteeHome")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F2C2C),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Already have account
        Button(
            onClick = {
                navController.navigate("login")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFF1F1),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login", color = Color(0xFF3F2C2C))
        }
    }
}
