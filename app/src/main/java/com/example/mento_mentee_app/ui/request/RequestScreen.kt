package com.example.mento_mentee_app.ui.request

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.ui.utils.MentorBottomBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.ui.utils.MenteeBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(
    navController: NavHostController,
    viewModel: MentorshipRequestViewModel = hiltViewModel()
) {
    val requests by viewModel.requests.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAllRequests()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mentorship Requests", color = Color.White) },
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
            MenteeBottomBar(navController)
        }
    ) { padding ->
        if (errorMessage != null) {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorMessage ?: "Unknown error", color = Color.Red)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Pending Requests",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }

                items(requests.filter { it.status == "pending" }) { request ->
                    PendingRequestCard(request, navController) { requestId ->
                        viewModel.deleteRequestById(requestId)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Addressed Requests",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(requests.filter { it.status != "pending" }) { request ->
                    AddressedRequestCard(request, navController) { requestId ->
                        viewModel.deleteRequestById(requestId)
                    }
                }
            }
        }
    }
}

@Composable
fun PendingRequestCard(request: FetchedMentorshipRequest, navController: NavHostController, onDelete: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Start Date: ${request.startDate}")
            Text("End Date: ${request.endDate}")
            Text("Field: ${request.mentorshipTopic}")
            Text("Notes: ${request.additionalNotes}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        navController.navigate(
                            "edit_request/${request._id}/${request.startDate}/${request.endDate}/" +
                                    "${request.mentorshipTopic}/${request.additionalNotes}/${request.mentorId}"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor = Color.White)
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = { onDelete(request._id ?: "") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun AddressedRequestCard(request: FetchedMentorshipRequest, navController: NavHostController, onDelete: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Start Date: ${request.startDate}")
            Text("End Date: ${request.endDate}")
            Text("Field: ${request.mentorshipTopic}")
            Text("Notes: ${request.additionalNotes}")
            Text("Status: ${request.status.replaceFirstChar { it.uppercase() }}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        navController.navigate(
                            "edit_request/${request._id}/${request.startDate}/${request.endDate}/" +
                                    "${request.mentorshipTopic}/${request.additionalNotes}/${request.mentorId}"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor = Color.White)
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = { onDelete(request._id ?: "") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Delete", color = Color.White)
                }
            }

        }
    }
}
