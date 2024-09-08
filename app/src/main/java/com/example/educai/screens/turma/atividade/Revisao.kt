package com.example.educai.screens.turma.atividade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.educai.components.CardAtividade
import com.example.educai.components.DefaultButton
import com.example.educai.components.Question
import java.time.LocalDate

@Composable
fun Revisao() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        CardAtividade(name = "Atividade 1", endDate = LocalDate.now())

        Spacer(modifier = Modifier.height(16.dp))

        val list = List(5) {}

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            list.forEach {
                Question()

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            DefaultButton(text = "Enviar") {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RevisaoPreview() {
    Revisao()
}
