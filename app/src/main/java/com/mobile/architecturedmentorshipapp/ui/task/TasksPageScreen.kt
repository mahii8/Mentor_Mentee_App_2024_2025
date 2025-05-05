package com.example.mento_mentee_app.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksPageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tasks & Achievements") })
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
                        Text("Task ${index + 1}: Complete Profile Setup")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Achievement: Certificate Awarded")
                    }
                }
            }
        }
    }
}
