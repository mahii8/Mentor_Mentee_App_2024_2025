package com.example.mento_mentee_app.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons // Import Icons
import androidx.compose.material.icons.filled.Settings // Import the specific icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.ui.utils.MentorBottomBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelationsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Relations", style = MaterialTheme.typography.titleLarge,color = Color.White) },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings",tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C))

            )
        },
        bottomBar = {
            MentorBottomBar(navController)
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(5) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Mentee ${index + 1}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3F2C2C),
                                    contentColor = Color.White
                                ),onClick = { navController.navigate("memberProfile") }) {
                                Text("View Profile")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3F2C2C),
                                    contentColor = Color.White
                                ),onClick = { navController.navigate("assignTask") },) {
                                Text("Assign Task")
                            }
                        }
                    }
                }
            }
        }
    }
}
