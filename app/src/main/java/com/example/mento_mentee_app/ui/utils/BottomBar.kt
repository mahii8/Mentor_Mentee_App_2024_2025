package com.example.mento_mentee_app.ui.utils

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.navigation.NavHostController

@Composable
fun MentorBottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFF3F2C2C)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null, tint = Color.White) },
            label = { Text("Home", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("mentorHome") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
            label = { Text("Profile", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Groups, contentDescription = null, tint = Color.White) },
            label = { Text("Members", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("members") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = null, tint = Color.White) },
            label = { Text("Relations", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("relations") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White) },
            label = { Text("Requests", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("process-request-screen")
            }
        )
    }
}

@Composable
fun MenteeBottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFF3F2C2C)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null, tint = Color.White) },
            label = { Text("Home", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("menteeHome") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
            label = { Text("Profile", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Groups, contentDescription = null, tint = Color.White) },
            label = { Text("Members", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("members") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = null, tint = Color.White) },
            label = { Text("Tasks", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("tasksPage") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White) },
            label = { Text("Requests", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("request") }
        )
    }
}
