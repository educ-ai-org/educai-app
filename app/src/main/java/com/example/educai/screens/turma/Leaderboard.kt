package com.example.educai.screens.turma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.ui.theme.LightPurple
import com.example.educai.R
import com.example.educai.components.StudentRanking

data class Student(
    val nome: String,
    val pontos: Int
)

@Composable
fun Leaderboard() {
    val students = listOf(
        Student("João", 100),
        Student("Maria", 90),
        Student("José", 80),
        Student("Ana", 70),
        Student("Pedro", 60),
        Student("Mariana", 50),
        Student("Carlos", 40),
        Student("Juliana", 30),
        Student("Paulo", 20),
        Student("Luana", 10)
    )

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Leaderboard",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.crown_icon),
                contentDescription = "Coroa",
                modifier = Modifier.height(18.dp)
            )
        }

        Column(
            modifier = Modifier
                .background(
                    color = LightPurple,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .verticalScroll(scrollState)
                .padding(bottom = 86.dp)
        ) {
            students.forEachIndexed { index, student ->
                StudentRanking(
                    posicao = index + 1,
                    nome = student.nome,
                    pontos = student.pontos
                )
                if (index < students.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
