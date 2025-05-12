package com.example.mento_mentee_app.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mento_mentee_app.model.task.UpdateTaskRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTasksScreen(
    navController: NavController,
    id: String,
    title: String,
    description: String,
    dueDate: String,
    priority: String,
    mentorId: String,
    menteeId: String,
    isCompleted: Boolean,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val updatedTask by viewModel.updatedTaskResponse.collectAsState()
    val error by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var taskTitle by remember { mutableStateOf(title) }
    var taskDescription by remember { mutableStateOf(description) }
    var localDueDate by remember {
        mutableStateOf(
            try {
                LocalDate.parse(dueDate)
            } catch (e: Exception) {
                null
            }
        )
    }
    var taskPriority by remember { mutableStateOf(priority) }

    val showDatePicker = remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ISO_DATE

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Task", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1F1)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        label = { Text("Task Title") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = localDueDate?.format(dateFormatter) ?: "",
                        onValueChange = {
                            try {
                                localDueDate = if (it.isNotEmpty()) LocalDate.parse(it, dateFormatter) else null
                            } catch (_: Exception) {}
                        },
                        label = { Text("Due Date (YYYY-MM-DD)") },
                        leadingIcon = {
                            IconButton(onClick = { showDatePicker.value = true }) {
                                Icon(Icons.Default.CalendarMonth, contentDescription = "Select Due Date")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = taskPriority,
                        onValueChange = { taskPriority = it },
                        label = { Text("Priority") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (taskTitle.isNotEmpty() && taskDescription.isNotEmpty() && localDueDate != null) {
                                viewModel.updateTask(
                                    taskId = id,
                                    request = UpdateTaskRequest(
                                        taskTitle = taskTitle,
                                        description = taskDescription,
                                        dueDate = localDueDate.toString(),
                                        priority = taskPriority,
                                    )
                                )
                            }
                        },
                        enabled = !isLoading && taskTitle.isNotEmpty() && taskDescription.isNotEmpty() && localDueDate != null,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3F2C2C),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Update Task")
                    }
                }
            }

            if (showDatePicker.value) {
                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = { showDatePicker.value = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                localDueDate = LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000L))
                            }
                            showDatePicker.value = false
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker.value = false }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            if (updatedTask != null) {
                LaunchedEffect(updatedTask) {
                    navController.navigate("tasksPage") {
                        popUpTo("tasksPage") { inclusive = true }
                    }
                }
            }

            error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
