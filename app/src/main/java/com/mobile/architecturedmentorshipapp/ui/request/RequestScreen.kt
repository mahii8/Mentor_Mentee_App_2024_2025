package com.example.mento_mentee_app.ui.request

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.components.MentorBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pending Requests") })
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
                        Text("Request from Mentee ${index + 1}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3F2C2C),
                                    contentColor = Color.White
                                ),onClick = { /* Accept */ }) {
                                Text("Accept")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3F2C2C),
                                    contentColor = Color.White
                                ),onClick = { /* Reject */ }) {
                                Text("Reject")
                            }
                        }
                    }
                }
            }
        }
    }
}
