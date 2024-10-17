package com.example.educai.data.model

data class Question (
    val id: String,
    val description: String,
    val options: List<Options>,
    val correctAnswerKey: String?
)

data class Options (
    val id: String,
    val key: String,
    val description: String
)