package com.example.educai.data.model

data class ClassworkReview (
    val id: String,
    val user: User,
    val datePosting: String,
    val classwork: Classwork,
    val questionAnswers: List<AnsweredQuestion>
)