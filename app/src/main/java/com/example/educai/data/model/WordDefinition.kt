package com.example.educai.data.model

data class WordDefinition (
    val partOfSpeech: String,
    val audio: String,
    val meanings: List<WordMeaning>
)