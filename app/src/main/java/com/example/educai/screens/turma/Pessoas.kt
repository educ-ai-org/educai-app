package com.example.educai.screens.turma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.educai.R
import com.example.educai.components.StudentRanking
import com.example.educai.ui.theme.LightPurple

data class Professor(
    val name: String
)

@Preview(showBackground = true)
@Composable
fun Pessoas() {
    val students = listOf(
        Student("Fernando Fernandes Souza", 2),
        Student("Giovanni Giorno Silva", 2),
        Student("Algusto Ferreira Lima", 2)
    )

    val professors = listOf(
        Professor("Rafael Rodriguez Ribeiro")
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Header("Professores")

        professors.forEachIndexed { index, professor ->

            Row(
                modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()

            ) {
                Image(
                    painter = painterResource(id = R.drawable.profileimage),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .height(18.dp)
                        .padding(end = 16.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
                Text(
                    text = professor.name,
                    color = Color.Black,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            if (index < students.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Header("Alunos")

        students.forEachIndexed { index, student ->

            Row(
                modifier = Modifier
                    .height(26.dp)
                    .fillMaxWidth()

            ) {
                Image(
                    painter = painterResource(id = R.drawable.profileimage),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .height(18.dp)
                        .padding(end = 16.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
                Text(
                    text = student.nome,
                    color = Color.Black,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            if (index < students.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun Header(title: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 16.dp, bottom = 16.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                val gradientBrush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6630EA), Color.White) // Gradient colors
                )
                drawLine(
                    brush = gradientBrush,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        if(title == "Professores") {
            Image(
                painter = painterResource(id = R.drawable.professor_icon),
                contentDescription = "Icone Turma",
                modifier = Modifier
                    .height(18.dp)
                    .padding(end = 16.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.aluno_icon),
                contentDescription = "Icone Turma",
                modifier = Modifier
                    .height(18.dp)
                    .padding(end = 16.dp)
            )
        }

        Text(
            text = title,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

