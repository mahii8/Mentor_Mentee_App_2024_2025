package com.example.mento_mentee_app.ui.member

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterMembersScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    var sortBy by remember { mutableStateOf("") }
    var needMentoring by remember { mutableStateOf(false) }
    var availableToMentor by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filter Members", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4A2B2B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Search bar
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                placeholder = { Text("Search by name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sort By
            Text("Sort By", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SortChip("Name (A-Z)", sortBy == "name") { sortBy = "name" }
                SortChip("Registration Date", sortBy == "date") { sortBy = "date" }
                SortChip("Age", sortBy == "age") { sortBy = "age" }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Filter By
            Text("Filter By", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            FilterToggleChip("Need Mentoring", needMentoring) { needMentoring = it }
            Spacer(modifier = Modifier.height(8.dp))
            FilterToggleChip("Available to Mentor", availableToMentor) { availableToMentor = it }

            Spacer(modifier = Modifier.height(16.dp))

            // Other filters
            OutlinedTextField(value = organization, onValueChange = { organization = it }, label = { Text("Organization") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = occupation, onValueChange = { occupation = it }, label = { Text("Occupation") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        search = ""
                        sortBy = ""
                        needMentoring = false
                        availableToMentor = false
                        organization = ""
                        occupation = ""
                        location = ""
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFECEC),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Clear All")
                }

                Button(
                    onClick = {
                        navController.navigate("memberProfile")
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    )
                ) {
                    Text("Apply Filters")
                }
            }
        }
    }
}

@Composable
fun SortChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (selected) Color(0xFF4A2B2B) else Color(0xFFFFECEC),
        contentColor = if (selected) Color.White else Color.Black,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
    ) {
        Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(label)
        }
    }
}

@Composable
fun FilterToggleChip(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (checked) Color(0xFF4A2B2B) else Color(0xFFFFECEC),
        contentColor = if (checked) Color.White else Color.Black,
        modifier = Modifier
            .clickable { onCheckedChange(!checked) }
    ) {
        Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(label)
        }
    }
}

