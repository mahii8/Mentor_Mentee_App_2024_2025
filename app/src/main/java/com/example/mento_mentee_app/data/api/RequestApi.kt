package com.example.mento_mentee_app.data.api

import com.example.mento_mentee_app.model.Mentor.CreateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipStatus

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestApi {
    @POST("/mentorship-requests") // Replace with your actual endpoint path
    suspend fun sendRequest(@Body request: CreateMentorshipRequest): Response<CreateMentorshipRequest>
    @GET("/mentorship-requests")
    suspend fun getAllRequests(): Response<List<FetchedMentorshipRequest>>
    @DELETE("/mentorship-requests/{id}")
    suspend fun deleteRequest(@Path("id") requestId: String): Response<Unit>

    @PATCH("/mentorship-requests/{id}")
    suspend fun updateRequest(
        @Path("id") requestId: String,
        @Body request: UpdateMentorshipRequest
    ): Response<FetchedMentorshipRequest>

    @GET("/mentorship-requests/mentors/sent")
    suspend fun getAllRequestsSentByMentees(): Response<List<FetchedMentorshipRequest>>

    @PATCH("/mentorship-requests/status/change/{id}")
    suspend fun updateRequestStatus(
        @Path("id") requestId: String,
        @Body statusUpdate: UpdateMentorshipStatus
    ): Response<FetchedMentorshipRequest>
    @GET("/mentorship-requests/mentees/accepted")
    suspend fun getAcceptedRequestsForMentees(): Response<List<FetchedMentorshipRequest>>

}
