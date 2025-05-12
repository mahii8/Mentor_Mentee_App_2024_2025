package com.example.mento_mentee_app.model.task


data class CreateTaskRequest(
    val taskTitle: String,
    val description: String,
    val dueDate: String,
    val priority: String,
    val menteeId: String
)
data class AssignedTaskResponse(
    val _id: String,
    val taskTitle: String,
    val description: String,
    val dueDate: String, // You can use LocalDateTime with a converter if needed
    val priority: String,
    val mentorId: String,
    val menteeId: String,
    val isCompleted: Boolean,
)

data class UpdateTaskRequest(
    val taskTitle: String? = null,
    val description: String? = null,
    val dueDate: String? = null,
    val priority: String? = null,
    val isCompleted: Boolean? = null
)
