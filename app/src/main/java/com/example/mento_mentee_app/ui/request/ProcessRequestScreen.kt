package com.example.mento_mentee_app.ui.request
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.ui.utils.MentorBottomBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipStatus
import com.example.mento_mentee_app.ui.request.MentorshipRequestViewModel

data class MentorshipRequest(
    val menteeName: String,
    val startDate: String,
    val endDate: String,
    val field: String,
    val notes: String,
    val status: String // "pending", "accepted", "rejected"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessRequestScreen(
    navController: NavHostController,
    viewModel: MentorshipRequestViewModel = hiltViewModel()
) {

    val requests by viewModel.requests.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAllRequestsSentByMentees()
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
            MentorBottomBar(navController)
        }
    ) { padding ->
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
                PendingRequestCard(request, viewModel )
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
                AddressedRequestCard(request)
            }
        }
    }
}

@Composable
fun PendingRequestCard(request: FetchedMentorshipRequest, viewModel: MentorshipRequestViewModel) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Mentee: ${request.menteeName}", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Text("Start Date: ${request.startDate}")
            Text("End Date: ${request.endDate}")
            Text("Field: ${request.mentorshipTopic}")
            Text("Notes: ${request.additionalNotes}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { viewModel.updateRequestStatus(request._id ?: "", UpdateMentorshipStatus("accepted")) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F2C2C), contentColor = Color.White)
                ) {
                    Text("Accept")
                }
                Button(
                    onClick = { viewModel.updateRequestStatus(request._id ?: "", UpdateMentorshipStatus("rejected"))},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Reject", color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun AddressedRequestCard(request: FetchedMentorshipRequest) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Mentee: ${request.menteeId}", fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
            Text("Status: ${request.status.replaceFirstChar { it.uppercase() }}")
        }
    }
}
