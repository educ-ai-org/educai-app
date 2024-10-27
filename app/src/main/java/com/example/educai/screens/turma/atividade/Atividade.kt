package com.example.educai.screens.turma.atividade

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.educai.MainActivity
import com.example.educai.components.CardAtividade
import com.example.educai.components.DefaultButton
import com.example.educai.components.Question
import com.example.educai.data.viewmodel.ClassworkViewModel
import java.time.LocalDate

@Composable
fun Atividade(id: String, voltar: () -> Unit, goToReview: (id: String) -> Unit) {
    val viewModel: ClassworkViewModel = viewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getClasswork(id)
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
            CardAtividade(name = viewModel.classwork.value?.title, endDate = viewModel.classwork.value?.endDate)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
                .verticalScroll(scrollState)
        ) {
            viewModel.classwork.value?.questions?.forEachIndexed { index, question ->
                Question(
                    question = question,
                    index = index,
                    changeQuestionOption = { viewModel.changeQuestionAnswer(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            DefaultButton(
                text = "Enviar",
                isLoading = viewModel.isLoading.value,
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                viewModel.sendClasswork(
                    classworkId = id,
                    successCallback = { goToReview(id) }
                )
            }
        }
    }
}
