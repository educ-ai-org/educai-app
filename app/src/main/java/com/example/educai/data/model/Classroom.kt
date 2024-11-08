package com.example.educai.data.model

data class Classroom (
    val id: String,
    val title: String,
    val course: String,
    val studentsCount: Int,
    val nextSubmission: String?
)