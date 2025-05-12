package com.example.mento_mentee_app.data.repository

import android.util.Log
import com.example.mento_mentee_app.data.api.MentorApi
import com.example.mento_mentee_app.model.Mentor.Mentor
import javax.inject.Inject

class MentorRepository @Inject constructor(
    private val mentorApi: MentorApi // Hilt will inject this dependency
) {

    suspend fun fetchMentors(): List<Mentor> {
        val response = mentorApi.getMentors()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            Log.e("MentorRepository", "API error: ${response.code()} - ${response.message()}")
            emptyList()
        }
    }

}
