package com.example.mento_mentee_app.data.repository

import com.example.mento_mentee_app.data.api.TaskApi
import com.example.mento_mentee_app.model.Mentor.CreateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipStatus
import com.example.mento_mentee_app.model.task.AssignedTaskResponse
import com.example.mento_mentee_app.model.task.CreateTaskRequest
import com.example.mento_mentee_app.model.task.UpdateTaskRequest
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject
import retrofit2.http.POST

class TaskRepository @Inject constructor(
    private val api: TaskApi
) {
    suspend fun createTask(request: CreateTaskRequest): Response<CreateTaskRequest> {
        return api.createTask(request)
    }

    suspend fun getAssignedTasks(): Response<List<AssignedTaskResponse>> {
        return api.getAssignedTasks()
    }

    suspend fun deleteTaskById(taskId: String): Response<Unit> {
        return api.deleteRequest(taskId)
    }

    suspend fun updateTaskById(taskId: String, request: UpdateTaskRequest): Response<AssignedTaskResponse> {
        return api.updateTask(taskId, request)
    }

}
