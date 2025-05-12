package com.example.mento_mentee_app.model.Mentor

data class CreateMentorshipRequest(
    val startDate: String,
    val endDate: String,
    val mentorshipTopic: String,
    val additionalNotes: String,
    val mentorId: String,
    val status: String = "pending"
)


data class FetchedMentorshipRequest(
    val _id: String,
    val startDate: String,
    val endDate: String,
    val mentorshipTopic: String,
    val additionalNotes: String,
    val mentorId: String,
    val status: String,
    val menteeId: String,
)

data class UpdateMentorshipRequest(
    val startDate: String,
    val endDate: String,
    val mentorshipTopic: String,
    val additionalNotes: String,
    val mentorId: String,
)
data class UpdateMentorshipStatus(
    val status: String,
)
