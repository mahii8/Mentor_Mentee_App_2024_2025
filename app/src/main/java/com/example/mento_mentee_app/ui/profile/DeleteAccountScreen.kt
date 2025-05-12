package com.example.mento_mentee_app.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Delete Account") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Are you sure you want to delete your account?")
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),onClick = { navController.navigate("welcome") }) {
                    Text("Confirm")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
            }
        }
    }
}
