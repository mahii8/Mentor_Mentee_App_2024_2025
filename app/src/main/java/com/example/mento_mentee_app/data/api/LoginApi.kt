package com.example.mento_mentee_app.data.api

import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Request body
data class LoginRequest(
    val email: String,
    val password: String,
    val role: String
)

// Response body
data class LoginResponse(
    val token: String
)

// Retrofit service
interface LoginService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

// Singleton API access
object LoginApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8888/") // Adjust port if different
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(LoginService::class.java)

    suspend fun login(email: String, password: String, role: String): Result<LoginResponse> {
        return try {
            val response = service.login(LoginRequest(email, password, role))
            Result.success(response)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = try {
                JSONObject(errorBody ?: "").getString("message")
            } catch (ex: Exception) {
                "Login failed with status ${e.code()}"
            }
            Result.failure(Exception(errorMessage))
        } catch (e: Exception) {
            Result.failure(Exception("Unexpected error: ${e.localizedMessage}"))
        }
    }
}
