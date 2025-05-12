package com.example.mento_mentee_app.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mento_mentee_app.model.task.CreateTaskRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.mento_mentee_app.ui.task.TaskViewModel
data class Mentee(val name: String, val field: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignTaskScreen(
    navController: NavController,
    menteeId: String, // Replace with dynamic ID if needed
    viewModel: TaskViewModel = hiltViewModel()
) {
    val taskState by viewModel.createTaskResponse.collectAsState()

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf<LocalDate?>(null) }
    var priority by remember { mutableStateOf("High Priority") }

    val showDatePicker = remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ISO_DATE

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Assign Task", color = Color.White) },
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
//            Text("Assign Task to $menteeName", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            Text("Field: $menteeField", fontSize = 14.sp, color = Color.Gray)

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
                        value = dueDate?.format(dateFormatter) ?: "",
                        onValueChange = {
                            try {
                                dueDate = if (it.isNotEmpty()) LocalDate.parse(it, dateFormatter) else null
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
                        value = priority,
                        onValueChange = { priority = it },
                        label = { Text("Priority") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (taskTitle.isNotEmpty() && taskDescription.isNotEmpty() && dueDate != null) {
                                viewModel.createTask(
                                    CreateTaskRequest(
                                    taskTitle = taskTitle,
                                    description = taskDescription,
                                    dueDate = dueDate.toString(),
                                    priority = priority,
                                    menteeId = menteeId
                                    )
                                )
                                navController.navigate("tasksPage")
                            }
                        },
                        enabled = taskTitle.isNotEmpty() && taskDescription.isNotEmpty() && dueDate != null,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3F2C2C),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Assign Task")
                    }

//                    if (taskState.isSuccess) {
//                        Text("Task assigned successfully!", color = Color.Green)
//                    }
//
//                    if (taskState.error != null) {
//                        Text("Error: ${taskState.error}", color = Color.Red)
//                    }
                }
            }

            // Date Picker
            if (showDatePicker.value) {
                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = { showDatePicker.value = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                dueDate = LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000L))
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
        }
    }
}
