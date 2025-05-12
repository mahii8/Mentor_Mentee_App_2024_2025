package com.example.mento_mentee_app.ui.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mento_mentee_app.data.repository.RequestRepository
import com.example.mento_mentee_app.model.Mentor.CreateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.FetchedMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipRequest
import com.example.mento_mentee_app.model.Mentor.UpdateMentorshipStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorshipRequestViewModel @Inject constructor(
    private val repository: RequestRepository)
: ViewModel() {

    private val _response = MutableStateFlow<String?>(null)
    val response = _response.asStateFlow()


    private val _requests = MutableStateFlow<List<FetchedMentorshipRequest>>(emptyList())
    val requests = _requests.asStateFlow()

    private val _acceptedRequests = MutableStateFlow<List<FetchedMentorshipRequest>>(emptyList())
    val acceptedRequests = _acceptedRequests.asStateFlow()



    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _updateStatus = MutableStateFlow<String?>(null)
    val updateStatus = _updateStatus.asStateFlow()

    suspend fun sendMentorshipRequest(request: CreateMentorshipRequest): Boolean {
        return try {
            val result = repository.sendRequest(request)
            _response.value = if (result.isSuccessful) "Request sent successfully"
            else "Failed: ${result.message()}"
            result.isSuccessful
        } catch (e: Exception) {
            _response.value = "Error: ${e.message}"
            false
        }
    }

    fun fetchAllRequests() {
        viewModelScope.launch {
            try {
                val result = repository.getAllRequests()
                if (result.isSuccessful) {
                    _requests.value = result.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Failed to fetch requests: ${result.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching requests: ${e.message}"
            }
        }
    }


    fun fetchAllRequestsSentByMentees() {
        viewModelScope.launch {
            try {
                val result = repository.getAllRequestsSentByMentees()
                if (result.isSuccessful) {
                    _requests.value = result.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Failed to fetch requests: ${result.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching requests: ${e.message}"
            }
        }
    }

    fun fetchAcceptedRequestsForMentees() {
        viewModelScope.launch {
            try {
                val result = repository.getAcceptedRequestsForMentees()
                if (result.isSuccessful) {
                    _acceptedRequests.value = result.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Failed to fetch accepted requests: ${result.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching accepted requests: ${e.message}"
            }
        }
    }

    fun deleteRequestById(requestId: String) {
        viewModelScope.launch {
            val success = repository.deleteMentorshipRequest(requestId)
            _response.value = if (success) "Request deleted successfully" else "Failed to delete request"
            if (success) {
                fetchAllRequests()
            }
        }
    }

    fun updateMentorshipRequestById(requestId: String, updates: UpdateMentorshipRequest) {
        viewModelScope.launch {
            try {
                val result = repository.updateMentorshipRequest(requestId, updates)
                if (result != null && result.isSuccessful) {
                    _updateStatus.value = "Request updated successfully"
                    fetchAllRequests()
                } else {
                    _updateStatus.value = "Failed to update: ${result?.message()}"
                }
            } catch (e: Exception) {
                _updateStatus.value = "Error updating request: ${e.message}"
            }
        }
    }
    fun updateRequestStatus(requestId: String, status: UpdateMentorshipStatus) {
        viewModelScope.launch {
            try {
                val response = repository.updateRequestStatus(requestId, status)
                if (response.isSuccessful) {
                    // Handle the updated request as needed
                    val updatedRequest = response.body()
                    fetchAllRequestsSentByMentees() // ⬅️ Refresh the list
                    // Update your UI or state with the updatedRequest
                } else {
                    // Handle error response
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error updating status: ${e.message}"
            }
        }
    }

}


