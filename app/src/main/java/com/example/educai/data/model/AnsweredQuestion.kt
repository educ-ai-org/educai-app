package com.example.educai.data.model

data class AnsweredClasswork(
    val datePosting: String,
    val questionAnswers: List<AnsweredQuestion>
)

data class AnsweredQuestion (
    var optionKey: String,
    var questionId: String
)