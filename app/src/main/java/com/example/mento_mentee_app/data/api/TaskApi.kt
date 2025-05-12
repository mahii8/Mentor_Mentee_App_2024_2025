package com.example.mento_mentee_app.data.api

import com.example.mento_mentee_app.model.task.AssignedTaskResponse
import com.example.mento_mentee_app.model.task.CreateTaskRequest
import com.example.mento_mentee_app.model.task.UpdateTaskRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApi {

    @POST("/tasks/")
    suspend fun createTask(
        @Body taskRequest: CreateTaskRequest
    ): Response<CreateTaskRequest>

    @GET("/tasks/")
    suspend fun getAssignedTasks(): Response<List<AssignedTaskResponse>>

    @DELETE("/tasks/{id}")
    suspend fun deleteRequest(@Path("id") taskId: String): Response<Unit>

    @PATCH("/tasks/{id}")
    suspend fun updateTask(
        @Path("id") taskId: String,
        @Body updateRequest: UpdateTaskRequest
    ): Response<AssignedTaskResponse>

}
