package com.example.educai.data.model

import com.google.gson.Gson
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

data class MessageDTO (
    val role: String,
    val content: String
)

fun getMessagesToAPI(messages: MutableList<Message>): List<MessageDTO> {
    return messages
        .filter { message ->
            message.type == MessageType.SEND || message.type == MessageType.RECEIVE
        }
        .map { message ->
            val role = when (message.type) {
                MessageType.SEND -> "user"
                MessageType.RECEIVE -> "assistant"
                else -> throw IllegalArgumentException("Unexpected MessageType")
            }
            MessageDTO(role = role, content = message.text)
        }
}