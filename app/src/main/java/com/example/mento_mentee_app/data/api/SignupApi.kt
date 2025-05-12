package com.example.mento_mentee_app.data.api

import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
// 1. Request body
data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val skill: String
)

// 2. Response
data class SignupResponse(
    val token: String
)

interface SignupService {
    @POST("auth/signup")
    suspend fun signup(@Body request: SignupRequest): SignupResponse
}

object SignupApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8888/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(SignupService::class.java)

    suspend fun signup(name: String, email: String, password: String, role: String, skill: String): Result<SignupResponse> {
        return try {
            val response = service.signup(SignupRequest(name, email, password, role, skill))
            Result.success(response)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = try {
                JSONObject(errorBody ?: "").getString("message")
            } catch (ex: Exception) {
                "Signup failed with status ${e.code()}"
            }
            Result.failure(Exception(errorMessage))
        } catch (e: Exception) {
            Result.failure(Exception("Unexpected error: ${e.localizedMessage}"))
        }
    }
}

