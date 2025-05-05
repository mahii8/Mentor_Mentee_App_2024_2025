package com.example.mento_mentee_app.ui.member

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mento_mentee_app.R // Make sure to import the correct resource path for your profile image

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Member Profile", style = MaterialTheme.typography.titleLarge,color = Color.White) },
                actions = {
                    IconButton(onClick = { /* Add edit action */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Profile",tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture Section
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for Profile Image (replace with an actual image resource)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = CircleShape,
                    color= Color(0xFF3F2C2C),
                ) {
                    Image(painter = painterResource(id = R.drawable.profile_placeholder), contentDescription = "Profile Image")
                }
            }

            // Profile Info Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Name: John Doe",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color(0xFF3F2C2C)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Age: 25",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Registered: Jan 2024",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { /* Handle Edit Profile action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)


                ) {
                    Text(text = "Send Request")
                }

                Button(
                    onClick = { /* Handle Contact action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemberProfileScreenPreview() {
    MemberProfileScreen(navController = rememberNavController())
}
