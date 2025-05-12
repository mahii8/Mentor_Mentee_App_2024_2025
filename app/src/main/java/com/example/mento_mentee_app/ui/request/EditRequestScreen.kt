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
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipRequest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRequestScreen(
    navController: NavHostController,
    id: String,
    startDateStr: String,
    endDateStr: String,
    mentorshipTopicArg: String,
    additionalNotesArg: String,
    mentorId: String,
    viewModel: MentorshipRequestViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val dateFormatter = DateTimeFormatter.ISO_DATE

    var startDate by remember {
        mutableStateOf(runCatching { LocalDate.parse(startDateStr.substringBefore("T")) }.getOrNull())
    }
    var endDate by remember {
        mutableStateOf(runCatching { LocalDate.parse(endDateStr.substringBefore("T")) }.getOrNull())
    }

    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }

    var mentorshipTopic by remember { mutableStateOf(mentorshipTopicArg) }
    var additionalNotes by remember { mutableStateOf(additionalNotesArg) }

    val response by viewModel.response.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Request", color = Color.White) },
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
            Text("Mentor ID: $mentorId", style = MaterialTheme.typography.titleMedium, color = Color(0xFF3F2C2C))

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
                    endDate = runCatching { LocalDate.parse(it, dateFormatter) }.getOrNull()
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
                        val request = UpdateMentorshipRequest(
                            startDate = startDate!!.toString() + "T00:00:00.000Z",
                            endDate = endDate!!.toString() + "T00:00:00.000Z",
                            mentorshipTopic = mentorshipTopic,
                            additionalNotes = additionalNotes,
                            mentorId = mentorId
                        )

                        viewModel.updateMentorshipRequestById(id, request)
                        navController.navigate("request")


                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFEDED),
                    contentColor = Color.Black
                )
            ) {
                Text("Edit Request")
            }

            response?.let {
                Text(it, color = if (it.contains("success", true)) Color.Green else Color.Red)
            }

            if (showStartPicker) {
                val datePickerState = rememberDatePickerState(
                    initialSelectedDateMillis = startDate?.toEpochDay()?.times(86_400_000L)
                )
                DatePickerDialog(
                    onDismissRequest = { showStartPicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                startDate = LocalDate.ofEpochDay(millis / 86_400_000L)
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
                    initialSelectedDateMillis = endDate?.toEpochDay()?.times(86_400_000L)
                )
                DatePickerDialog(
                    onDismissRequest = { showEndPicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                endDate = LocalDate.ofEpochDay(millis / 86_400_000L)
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

