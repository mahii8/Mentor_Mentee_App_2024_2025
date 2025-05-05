package com.example.mento_mentee_app.ui.member

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterMembersScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var registrationDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Filter Members") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Age") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = registrationDate, onValueChange = { registrationDate = it }, label = { Text("Registration Date") })
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),
                    onClick = {
                    // Clear fields
                    name = ""
                    age = ""
                    registrationDate = ""
                }) {
                    Text("Clear All")
                }
                Button(

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),onClick = { navController.navigate("memberProfile") }) {
                    Text("Apply Filters")
                }
            }
        }
    }
}
