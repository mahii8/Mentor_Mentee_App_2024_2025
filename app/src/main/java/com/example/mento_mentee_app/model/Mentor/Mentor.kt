package com.example.mento_mentee_app.model.Mentor

public data class Mentor(
    val _id: String,
    val name: String? = null,
    val email: String,
    val role: String,
    val skill: String? = null,
    val createdAt: String,
    val updatedAt: String
)
