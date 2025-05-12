package com.example.mento_mentee_app.data.repository

import com.example.mento_mentee_app.data.api.RequestApi
import com.example.mento_mentee_app.model.Mentor.CreateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipStatus
import retrofit2.Response
import javax.inject.Inject

class RequestRepository @Inject constructor(
    private val api: RequestApi
) {
    suspend fun sendRequest(request: CreateMentorshipRequest): Response<CreateMentorshipRequest> {
        return api.sendRequest(request)
    }

    suspend fun getAllRequests(): Response<List<FetchedMentorshipRequest>> {
        return api.getAllRequests()
    }

    suspend fun getAllRequestsSentByMentees(): Response<List<FetchedMentorshipRequest>> {
        return api.getAllRequestsSentByMentees()
    }

    suspend fun deleteMentorshipRequest(requestId: String): Boolean {
        return try {
            val response = api.deleteRequest(requestId)
            response.isSuccessful
        } catch (e: Exception) {
            // Handle exceptions (e.g., network errors)
            false
        }
    }
    suspend fun updateMentorshipRequest(
        requestId: String,
        updates: UpdateMentorshipRequest
    ): Response<FetchedMentorshipRequest>? {
        return try {
            api.updateRequest(requestId, updates)
        } catch (e: Exception) {
            null
        }

    }
    suspend fun updateRequestStatus(requestId: String, status: UpdateMentorshipStatus): Response<FetchedMentorshipRequest> {
        return api.updateRequestStatus(requestId, status)
    }
}
