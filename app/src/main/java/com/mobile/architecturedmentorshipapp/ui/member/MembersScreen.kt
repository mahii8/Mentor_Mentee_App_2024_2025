package com.example.mento_mentee_app.ui.member

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mento_mentee_app.R // Import this to access resources like images

data class Member(val name: String, val imageResId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersScreen(navController: NavController) {
    val members = listOf(
        Member("John Doe", R.drawable.profile_placeholder), // Replace with your actual image resource ID
        Member("Jane Smith", R.drawable.profile_placeholder), // Replace with your actual image resource ID
        Member("Mark Johnson", R.drawable.profile_placeholder), // Replace with your actual image resource ID
        Member("Emily Davis", R.drawable.profile_placeholder) // Replace with your actual image resource ID
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Members", color = Color.White) },
                actions = {
                    IconButton(onClick = { navController.navigate("filterMembers") }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C)) // Teal color
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(members.size) { index ->
                val member = members[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("memberProfile") },
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)) // Light background color
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Profile Image (Placeholder or actual image)
                        Surface(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 16.dp),
                            shape = CircleShape,
                            color = Color(0xFF3F2C2C) // Teal color for the circle
                        ) {
                            Image(
                                painter = painterResource(id = member.imageResId),
                                contentDescription = "Profile Image",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Member Name
                        Text(
                            text = member.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
