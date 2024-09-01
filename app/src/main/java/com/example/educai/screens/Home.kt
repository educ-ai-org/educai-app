package com.example.educai.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educai.components.Class

data class Turma(
    val id: Int,
    val nome: String,
    val disciplina: String,
    val numeroDeAlunos: Int
)

@Composable
fun Home(navController: NavController) {

    val turmasMockadas = listOf(
        Turma(1,"Turma 01", "Inglês", 30),
        Turma(2,"Turma 02", "Matemática", 25),
        Turma(3,"Turma 03", "História", 20),
        Turma(4,"Turma 04", "Ciências", 15),
        Turma(5,"Turma 05", "Arte", 10)
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            )
    ) {
        Text(
            text = "Turmas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            turmasMockadas.forEach { turma ->
                Class(
                    nomeTurma = turma.nome,
                    nomeDisciplina = turma.disciplina,
                    quantidadeAlunos = turma.numeroDeAlunos,
                    onClick = {
                        navController.navigate("turmaExemplo")
                        //navController.navigate("detalhesTurma/${turma.id}")
                    }
                )
            }
        }
    }
}