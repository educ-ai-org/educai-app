package com.example.educai.data.model

import java.time.LocalDateTime

enum class MessageType {
    RECEIVE,
    SEND,
    WRITING_SEND,
    WRITING_RECEIVE
}

data class Message(
    val text: String,
    val date: LocalDateTime,
    val type: MessageType
)