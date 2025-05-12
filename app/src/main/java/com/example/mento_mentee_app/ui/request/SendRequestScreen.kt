package com.example.mento_mentee_app.ui.request

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mento_mentee_app.model.Mentor.CreateMentorshipRequest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendRequestScreen(
    navController: NavHostController,
    _id: String,
    name: String,
    specialization: String,
    viewModel: MentorshipRequestViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val dateFormatter = DateTimeFormatter.ISO_DATE

    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }

    var mentorshipTopic by remember { mutableStateOf("") }
    var additionalNotes by remember { mutableStateOf("") }

    val response by viewModel.response.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Send Request", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Mentor ID: $_id", style = MaterialTheme.typography.titleMedium, color = Color(0xFF3F2C2C))
            Text("Mentor: $name", style = MaterialTheme.typography.titleMedium, color = Color(0xFF3F2C2C))
            Text("Occupation: $specialization", style = MaterialTheme.typography.bodyMedium)

            OutlinedTextField(
                value = startDate?.format(dateFormatter) ?: "",
                onValueChange = {
                    startDate = runCatching { LocalDate.parse(it, dateFormatter) }.getOrNull()

                },
                label = { Text("Start Date") },
                placeholder = { Text("YYYY-MM-DD") },
                leadingIcon = {
                    IconButton(onClick = { showStartPicker = true }) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = "Pick Start Date")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = endDate?.format(dateFormatter) ?: "",
                onValueChange = {
                    try { endDate = LocalDate.parse(it, dateFormatter) } catch (_: Exception) { endDate = null }
                },
                label = { Text("End Date") },
                placeholder = { Text("YYYY-MM-DD") },
                leadingIcon = {
                    IconButton(onClick = { showEndPicker = true }) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = "Pick End Date")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = mentorshipTopic,
                onValueChange = { mentorshipTopic = it },
                label = { Text("What do you need mentorship in?") },
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )

            OutlinedTextField(
                value = additionalNotes,
                onValueChange = { additionalNotes = it },
                label = { Text("Additional Notes") },
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )

            Button(
                onClick = {
                    if (startDate != null && endDate != null) {
                        val request = CreateMentorshipRequest(
                            startDate = startDate!!.toString() + "T00:00:00.000Z",
                            endDate = endDate!!.toString() + "T00:00:00.000Z",
                            mentorshipTopic = mentorshipTopic,
                            additionalNotes = additionalNotes,
                            mentorId = _id
                        )
                        coroutineScope.launch {
                            val success = viewModel.sendMentorshipRequest(request)
                            // React to success/failure
                            if (success) {
                                // Handle success
                                navController.navigate("request")
                            } else {
                                // Handle failure
                            }
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFEDED),
                    contentColor = Color.Black
                )
            ) {
                Text("Send Request")
            }

            response?.let {
                Text(it, color = if (it.contains("success", true)) Color.Green else Color.Red)
            }

            if (showStartPicker) {
                val datePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = startDate?.toEpochDay()?.times(86400000L)
                )
                DatePickerDialog(
                    onDismissRequest = { showStartPicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                startDate = LocalDate.ofEpochDay(millis / 86400000L)
                            }
                            showStartPicker = false
                        }) { Text("OK") }
                    },
                    dismissButton = {
                        TextButton(onClick = { showStartPicker = false }) { Text("Cancel") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            if (showEndPicker) {
                val datePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = endDate?.toEpochDay()?.times(86400000L)
                )
                DatePickerDialog(
                    onDismissRequest = { showEndPicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                endDate = LocalDate.ofEpochDay(millis / 86400000L)
                            }
                            showEndPicker = false
                        }) { Text("OK") }
                    },
                    dismissButton = {
                        TextButton(onClick = { showEndPicker = false }) { Text("Cancel") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}
