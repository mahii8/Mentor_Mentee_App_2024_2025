package com.example.mento_mentee_app.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mento_mentee_app.data.repository.TaskRepository
import com.example.mento_mentee_app.model.task.AssignedTaskResponse
import com.example.mento_mentee_app.model.task.CreateTaskRequest
import com.example.mento_mentee_app.model.task.UpdateTaskRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository)
    : ViewModel() {
    private val _createTaskResponse = MutableStateFlow<CreateTaskRequest?>(null)
    val createTaskResponse: StateFlow<CreateTaskRequest?> = _createTaskResponse

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _updatedTaskResponse = MutableStateFlow<AssignedTaskResponse?>(null)
    val updatedTaskResponse: StateFlow<AssignedTaskResponse?> = _updatedTaskResponse

    private val _assignedTasks = MutableStateFlow<List<AssignedTaskResponse>>(emptyList())
    val assignedTasks: StateFlow<List<AssignedTaskResponse>> = _assignedTasks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun createTask(task: CreateTaskRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.createTask(task)
                if (response.isSuccessful) {
                    _createTaskResponse.value = response.body()
                } else {
                    _errorMessage.value = "Failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAssignedTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAssignedTasks()
                if (response.isSuccessful) {
                    _assignedTasks.value = response.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateTask(taskId: String, request: UpdateTaskRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.updateTaskById(taskId, request)
                if (response.isSuccessful) {
                    _updatedTaskResponse.value = response.body()
                } else {
                    _errorMessage.value = "Failed to update task: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Update error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteRequestById(requestId: String) {
        viewModelScope.launch {

            _isLoading.value = true
            try {
                val response = repository.deleteTaskById(requestId)
                if (response.isSuccessful) {
                    getAssignedTasks()
                } else {
                    _errorMessage.value = "Failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}