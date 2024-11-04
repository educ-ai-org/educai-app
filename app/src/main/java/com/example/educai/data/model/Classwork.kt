package com.example.educai.data.model

data class Classwork (
    val id: String,
    val title: String,
    val datePosting: String,
    var endDate: String,
    val description: String,
    val totalAnswers: Int?,
    val totalQuestions: Int?,
    val questions: List<Question>,
    val correctPercentage: Double?,
    val hasAnswered: Boolean?
)