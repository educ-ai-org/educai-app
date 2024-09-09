package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.R
import com.example.educai.ui.theme.MediumPurple

@Composable
fun InfoTurma() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(1.dp, MediumPurple, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_turma),
            contentDescription = "Atividades",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier.align(Alignment.Bottom)
        ) {
            Text(
                text = "Turma 01",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Disciplina",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.size(50.dp))
        Text(
            text = "24 alunos",
            modifier = Modifier.align(Alignment.Bottom),
            fontSize = 20.sp
        )
    }
}

@Composable
@Preview
fun InfoTurmaPreview() {
    InfoTurma()
}