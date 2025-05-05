package com.example.mento_mentee_app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mento_mentee_app.ui.utils.MentorBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White) },
                actions = {
                    IconButton(onClick = { navController.navigate("editProfile") }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                    }
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C)) // Dark background for the header
            )
        },
        bottomBar = {
            MentorBottomBar(navController) // Assuming MentorBottomBar is reusable
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF1F1F1))  // Set the background color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Welcome Message
                Text(
                    text = "Welcome to your Profile, Mentor!",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 24.dp)
                )

                // Profile Information Section
                ProfileSection(title = "Name", content = "John Doe")
                ProfileSection(title = "Email", content = "johndoe@example.com")

                ProfileSection(title = "skills", content = "javascript")
                ProfileSection(title = "Available to", content = "Mentor")
                ProfileSection(title = "Bio", content = "Passionate about mentoring and helping others grow.")
                ProfileSection(title = "Location", content = "Addis Ababa, Ethiopia")
                ProfileSection(title = "Occupation", content = "Software Engineer")
                ProfileSection(title = "Organization", content = "Mento-Mentee Organization")

                Spacer(modifier = Modifier.height(24.dp))

                // Edit Profile Button with updated color
                Button(
                    onClick = { navController.navigate("editProfile") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C), // Dark button (same as top bar color)
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Edit Profile")
                }
            }
        }
    }
}

@Composable
fun ProfileSection(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF3F2C2C),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF757575)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
