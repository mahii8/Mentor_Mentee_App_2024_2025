package com.example.mento_mentee_app.ui.member

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.data.datastore.TokenManager
import com.example.mento_mentee_app.model.Mentor.Mentor
import kotlinx.coroutines.flow.first
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MentorCard(mentor: Mentor, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ID: ${mentor._id}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Name: ${mentor.name ?: "N/A"}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Skill: ${mentor.skill ?: "Not specified"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersScreen(
    navController: NavHostController,
    viewModel: MembersViewModel = hiltViewModel() // Injected with Hilt
) {
    val mentors by viewModel.mentors.collectAsState()
    val context = LocalContext.current

    // Fetch mentor list once with token
    LaunchedEffect(Unit) {
        val tokenManager = TokenManager(context)
        val token = tokenManager.token.first()
        if (!token.isNullOrEmpty()) {
            viewModel.fetchMentors()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mentors List") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(mentors) { mentor ->
                MentorCard(mentor = mentor, onClick = {
                    if (mentor._id.isNotEmpty()) {
                        val encodedName =
                            URLEncoder.encode(mentor.name ?: "", StandardCharsets.UTF_8.toString())
                        val encodedSkill =
                            URLEncoder.encode(mentor.skill ?: "", StandardCharsets.UTF_8.toString())
                        navController.navigate("sendRequestScreen/${mentor._id}/$encodedName/$encodedSkill")
                    }
                })
            }
        }
    }
}
