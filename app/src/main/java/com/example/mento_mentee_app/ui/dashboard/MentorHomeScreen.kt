package com.example.mento_mentee_app.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.ui.utils.MentorBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentorHomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home", color = Color.White) },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C)) // Set the background color of the top bar
            )
        },
        bottomBar = {
            MentorBottomBar(navController)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF1F1F1))  // Set the overall background color to #FFF1F1
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Welcome Message
                Text(
                    text = "Welcome, Mentor!",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.Start).padding(bottom = 16.dp)
                )

                // "Mentor Home" message
                Text(
                    text = "Mentor Home",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 16.dp)
                )

                // Spacer for some separation
                Spacer(modifier = Modifier.height(16.dp)) // Reduced space

                // Request Status Section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)) // Set color to #FFF1F1 for request section
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Request Status", style = MaterialTheme.typography.titleLarge)

                        Spacer(modifier = Modifier.height(8.dp))

                        // Pending Requests
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Pending Requests", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "0", style = MaterialTheme.typography.bodyLarge)
                        }

                        // Accepted Requests
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Accepted Requests", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "0", style = MaterialTheme.typography.bodyLarge)
                        }

                        // Rejected Requests
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Rejected Requests", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "0", style = MaterialTheme.typography.bodyLarge)
                        }

                        // Completed Relations
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Completed Relations", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "0", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Credentials Section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)) // Set color to #FFF1F1 for credentials section
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Credentials", style = MaterialTheme.typography.titleLarge)

                        Spacer(modifier = Modifier.height(8.dp))

                        // No credentials message
                        Text(text = "No credentials added", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Add Credential Button
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C), // Dark button (same color as Login)
                        contentColor = Color.White
                    ),
                    onClick = { /* Handle Add Credential action */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add Credential")
                }
            }
        }
    }
}
