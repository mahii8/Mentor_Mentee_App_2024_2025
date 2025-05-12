package com.example.mento_mentee_app.data.api

import com.example.mento_mentee_app.model.Mentor.Mentor
import retrofit2.Response
import retrofit2.http.GET

interface MentorApi {
    @GET("mentors")
    suspend fun getMentors(): Response<List<Mentor>>
}




