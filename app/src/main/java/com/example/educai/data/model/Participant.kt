package com.example.educai.data.model

data class Participant (
    val id: String,
    val name: String,
    val email: String,
    val password: String?,
    val role: String,
    val profilePicture: String?
)