package com.example.educai.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.educai.components.Class
import com.example.educai.data.viewmodel.UserViewModel

data class Turma(
    val id: Int,
    val nome: String,
    val disciplina: String,
    val numeroDeAlunos: Int
)

@Composable
fun Home(
    navController: NavController,
    viewModel: UserViewModel = viewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(true)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    LaunchedEffect(Unit) {
        viewModel.getUserClassrooms()
    }

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
            viewModel.classrooms.value.forEach { turma ->
                Class(
                    nomeTurma = turma.title,
                    nomeDisciplina = turma.course,
                    quantidadeAlunos = turma.studentsCount,
                    onClick = {
                        navController.navigate("turmaExemplo")
                        //navController.navigate("detalhesTurma/${turma.id}")
                    }
                )
            }
        }
    }
}
