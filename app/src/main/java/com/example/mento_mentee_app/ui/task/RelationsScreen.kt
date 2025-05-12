package com.example.mento_mentee_app.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.ui.request.MentorshipRequestViewModel
import com.example.mento_mentee_app.ui.utils.MentorBottomBar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelationsScreen(
    navController: NavHostController,
    viewModel: MentorshipRequestViewModel = hiltViewModel()
) {
    val requests by viewModel.acceptedRequests.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAcceptedRequestsForMentees()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Accepted Requests", style = MaterialTheme.typography.titleLarge, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
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
            if (requests.isEmpty()) {
                item {
                    Text(
                        "No accepted requests found.",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Gray
                    )
                }
            } else {
                items(requests) { request ->
                    RequestCard(request, navController)
                }
            }
        }

        error?.let {
            Snackbar(modifier = Modifier.padding(16.dp)) {
                Text(text = it)
            }
        }
    }
}

@Composable
fun RequestCard(request: FetchedMentorshipRequest, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Mentorship Topic: ${request.mentorshipTopic}")
            Spacer(modifier = Modifier.height(4.dp))
            Text("Start: ${request.startDate}")
            Text("End: ${request.endDate}")
            Text("Notes: ${request.additionalNotes}")
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),
                    onClick = { navController.navigate("memberProfile") }
                ) {
                    Text("View Profile")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F2C2C),
                        contentColor = Color.White
                    ),
                    onClick = { navController.navigate("assignTask/${request.menteeId}") }
                ) {
                    Text("Assign Task")
                }
            }
        }
    }
}
