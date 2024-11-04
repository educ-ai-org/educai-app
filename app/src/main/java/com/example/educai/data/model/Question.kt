package com.example.educai.data.model

data class Question(
    val id: String,
    val description: String,
    val correctAnswerKey: String,
    val options: List<Option>
)

data class Option(
    val id: String,
    val key: String,
    val description: String
)