package com.example.mento_mentee_app.ui.member

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mento_mentee_app.model.Mentor.Mentor
import com.example.mento_mentee_app.data.repository.MentorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val repository: MentorRepository // Hilt will inject this
) : ViewModel() {

    private val _mentors = MutableStateFlow<List<Mentor>>(emptyList())
    val mentors: StateFlow<List<Mentor>> = _mentors

    fun fetchMentors() {
        viewModelScope.launch {
            try {
                val fetchedMentors = repository.fetchMentors()
                _mentors.value = fetchedMentors
            } catch (e: Exception) {
                Log.e("MembersViewModel", "Error fetching mentors", e)
                _mentors.value = emptyList()
            }
        }
    }
}
