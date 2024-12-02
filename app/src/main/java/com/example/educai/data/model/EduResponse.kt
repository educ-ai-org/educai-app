package com.example.educai.data.model

data class EduResponse (
    val response: String
)

data class EduRequest (
    val messages: List<MessageDTO>,
    val openai: Boolean
)