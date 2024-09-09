package com.example.educai.mock

import com.example.educai.screens.turma.AtividadeData
import com.example.educai.screens.turma.Question

fun getMockAtividades(): List<AtividadeData> {
    return listOf(
        AtividadeData(
            title = "Atividade 1",
            datePosting = "2023-10-01",
            endDate = "2023-10-10",
            description = "Descrição da Atividade 1",
            totalAnswers = 10,
            totalQuestions = 5,
            questions = listOf(
                Question(id = "1", description = "Questão 1", correctAnswerKey = "A", options = listOf()),
                Question(id = "2", description = "Questão 2", correctAnswerKey = "B", options = listOf())
            ),
            correctPercentage = 80.0,
            hasAnswered = true
        ),
        AtividadeData(
            title = "Atividade 2",
            datePosting = "2023-10-05",
            endDate = "2023-10-15",
            description = "Descrição da Atividade 2",
            totalAnswers = 8,
            totalQuestions = 4,
            questions = listOf(
                Question(id = "3", description = "Questão 3", correctAnswerKey = "C", options = listOf()),
                Question(id = "4", description = "Questão 4", correctAnswerKey = "D", options = listOf())
            ),
            correctPercentage = 75.0,
            hasAnswered = false
        ),
        AtividadeData(
            title = "Atividade 3",
            datePosting = "2023-10-10",
            endDate = "2023-10-20",
            description = "Descrição da Atividade 3",
            totalAnswers = 12,
            totalQuestions = 6,
            questions = listOf(
                Question(id = "5", description = "Questão 5", correctAnswerKey = "E", options = listOf()),
                Question(id = "6", description = "Questão 6", correctAnswerKey = "F", options = listOf())
            ),
            correctPercentage = 85.0,
            hasAnswered = true
        ),
        AtividadeData(
            title = "Atividade 4",
            datePosting = "2023-10-15",
            endDate = "2023-10-25",
            description = "Descrição da Atividade 4",
            totalAnswers = 9,
            totalQuestions = 5,
            questions = listOf(
                Question(id = "7", description = "Questão 7", correctAnswerKey = "G", options = listOf()),
                Question(id = "8", description = "Questão 8", correctAnswerKey = "H", options = listOf())
            ),
            correctPercentage = 70.0,
            hasAnswered = false
        ),
        AtividadeData(
            title = "Atividade 5",
            datePosting = "2023-10-20",
            endDate = "2023-10-30",
            description = "Descrição da Atividade 5",
            totalAnswers = 15,
            totalQuestions = 7,
            questions = listOf(
                Question(id = "9", description = "Questão 9", correctAnswerKey = "I", options = listOf()),
                Question(id = "10", description = "Questão 10", correctAnswerKey = "J", options = listOf())
            ),
            correctPercentage = 90.0,
            hasAnswered = true
        ),
        AtividadeData(
            title = "Atividade 6",
            datePosting = "2023-10-25",
            endDate = "2023-11-05",
            description = "Descrição da Atividade 6",
            totalAnswers = 7,
            totalQuestions = 3,
            questions = listOf(
                Question(id = "11", description = "Questão 11", correctAnswerKey = "K", options = listOf()),
                Question(id = "12", description = "Questão 12", correctAnswerKey = "L", options = listOf())
            ),
            correctPercentage = 65.0,
            hasAnswered = false
        ),
        AtividadeData(
            title = "Atividade 7",
            datePosting = "2023-10-30",
            endDate = "2023-11-10",
            description = "Descrição da Atividade 7",
            totalAnswers = 11,
            totalQuestions = 6,
            questions = listOf(
                Question(id = "13", description = "Questão 13", correctAnswerKey = "M", options = listOf()),
                Question(id = "14", description = "Questão 14", correctAnswerKey = "N", options = listOf())
            ),
            correctPercentage = 75.0,
            hasAnswered = true
        ),
        AtividadeData(
            title = "Atividade 8",
            datePosting = "2023-11-01",
            endDate = "2023-11-15",
            description = "Descrição da Atividade 8",
            totalAnswers = 13,
            totalQuestions = 7,
            questions = listOf(
                Question(id = "15", description = "Questão 15", correctAnswerKey = "O", options = listOf()),
                Question(id = "16", description = "Questão 16", correctAnswerKey = "P", options = listOf())
            ),
            correctPercentage = 80.0,
            hasAnswered = false
        )
    )
}