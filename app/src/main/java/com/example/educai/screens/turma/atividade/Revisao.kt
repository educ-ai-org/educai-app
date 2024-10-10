package com.example.educai.screens.turma.atividade

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.educai.components.CardAtividade
import com.example.educai.components.DefaultButton
import com.example.educai.components.Question
import com.example.educai.data.viewmodel.ClassworkViewModel
import java.time.LocalDate

@Composable
fun Revisao(voltar: () -> Unit) {
    val id = "66776e3fa1bc74153a5c5a60";

    val viewModel: ClassworkViewModel = viewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getClassworkReview(id)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .clickable { voltar() }
        ) {
            CardAtividade(name = viewModel.classworkReview.value?.classwork?.title, endDate = viewModel.classworkReview.value?.classwork?.endDate)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            viewModel.classworkReview.value?.classwork?.questions?.forEachIndexed { index, question ->
                val studentAnswer = viewModel.classworkReview.value?.questionAnswers?.find { it.questionId == question.id }

                Question(
                    question = question,
                    index = index,
                    studentAnswer = studentAnswer
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RevisaoPreview() {
    //Revisao()
}
