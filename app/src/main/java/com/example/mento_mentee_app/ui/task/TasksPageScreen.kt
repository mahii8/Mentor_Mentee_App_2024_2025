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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.model.task.AssignedTaskResponse
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksPageScreen(
    navController: NavHostController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.assignedTasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    // Fetch tasks on first load
    LaunchedEffect(Unit) {
        viewModel.getAssignedTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("settings")
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C))
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Your Tasks", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }

            when {
                isLoading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                error != null -> {
                    item {
                        Text("Error: $error", color = Color.Red)
                    }
                }
                tasks.isEmpty() -> {
                    item {
                        Text("No tasks assigned.")
                    }
                }
                else -> {
                    items(tasks) { task ->
                        TaskCard(task, navController) { requestId ->
                            viewModel.deleteRequestById(requestId)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text("Achievements", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(
                listOf(
                    AchievementItem("Completed Python Course", "Success is not the key to happiness. Happiness is the key to success."),
                    AchievementItem("Published First Article", "Believe you can and you're halfway there.")
                )
            ) { achievement ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F0))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(achievement.title, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("“${achievement.quote}”")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TaskCard(task: AssignedTaskResponse, navController: NavHostController, onDelete: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(task.taskTitle, fontWeight = FontWeight.Bold)
            Text("Due: ${task.dueDate.substringBefore("T")}")
            Text("Priority: ${task.priority}")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        val encodedTitle = URLEncoder.encode(task.taskTitle, StandardCharsets.UTF_8.toString())
                        val encodedDesc = URLEncoder.encode(task.description, StandardCharsets.UTF_8.toString())
                        val encodedDate = URLEncoder.encode(task.dueDate, StandardCharsets.UTF_8.toString())

                        navController.navigate(
                            "edit_task/${task._id}/$encodedTitle/$encodedDesc/$encodedDate/${task.priority}/${task.mentorId}/${task.menteeId}/${task.isCompleted}"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor = Color.White)
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = { onDelete(task._id ?: "") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Delete", color = Color.White)
                }
            }
        }
    }
}

data class AchievementItem(val title: String, val quote: String)