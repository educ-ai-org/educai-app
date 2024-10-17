package com.example.educai.data.model

data class Classwork (
    val id: String,
    val title: String,
    val datePosting: String,
    var endDate: String,
    val description: String,
    val questions: List<Question>
)