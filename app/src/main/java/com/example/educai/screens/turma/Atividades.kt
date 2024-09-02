package com.example.educai.screens.turma

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Atividades(atividades: List<AtividadeData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        atividades.forEach { atividade ->
            Atividade(atividadeData = atividade)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

suspend fun fetchAtividadesFromDatabase(): List<AtividadeData> {
    return withContext(Dispatchers.IO) {
        listOf(
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
            )
        )
    }
}

data class Option(
    val id: String? = null,
    val key: String,
    val description: String
)

data class Question(
    val id: String? = null,
    val description: String,
    val correctAnswerKey: String,
    val options: List<Option>
)

data class AtividadeData(
    val id: String? = null,
    val title: String,
    val datePosting: String,
    val endDate: String,
    val description: String,
    val totalAnswers: Int? = null,
    val totalQuestions: Int? = null,
    val questions: List<Question>,
    val correctPercentage: Double? = null,
    val hasAnswered: Boolean? = null
)

@Composable
fun Atividade(atividadeData: AtividadeData) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(color = Color.White)
        .border(width = 2.dp, color = Color(0xFFBEBEBE), shape = MaterialTheme.shapes.medium)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = Color(0xFFBEBEBE),
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "${atividadeData.title}")
            Text(
                text = buildAnnotatedString {
                    append("Prazo: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(atividadeData.endDate)
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Data de publicação: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(atividadeData.datePosting)
                    }
                },
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Text(text = "Status: ")
                Text(
                    text = if (atividadeData.hasAnswered == true) "Enviado" else "Pendente",
                    color = if (atividadeData.hasAnswered == true) Color.Green else Color.Yellow,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(text = "${atividadeData.description}")
        }
    }
}

@Preview
@Composable
fun PreviewAtividade() {
    Atividade(
        atividadeData = AtividadeData(
            title = "Sample Activity",
            datePosting = "2023-10-01",
            endDate = "2023-10-10",
            description = "This is a sample activity description.",
            totalAnswers = 10,
            totalQuestions = 5,
            questions = listOf(
                Question(id = "1", description = "Sample Question 1", correctAnswerKey = "A", options = listOf()),
                Question(id = "2", description = "Sample Question 2", correctAnswerKey = "B", options = listOf())
            ),
            correctPercentage = 80.0,
            hasAnswered = true
        )
    )
}

@Preview
@Composable
fun PreviewAtividades() {
    val atividades = listOf(
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
        )
    )

    Atividades(atividades = atividades)
}