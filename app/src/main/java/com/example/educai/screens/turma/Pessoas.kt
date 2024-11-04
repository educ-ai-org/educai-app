package com.example.educai.screens.turma

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.educai.R
import com.example.educai.data.viewmodel.UserViewModel

@Composable
fun Pessoas(
    viewModel: UserViewModel = viewModel(),
    classRoomId: String
) {
    val isLoading by viewModel.isLoading.observeAsState(true)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    LaunchedEffect(Unit) {
        viewModel.getParticipantsByClassId(classRoomId)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Header("Professores")

        ParticipantRow(viewModel = viewModel, role = "TEACHER")

        Header("Alunos")

        ParticipantRow(viewModel = viewModel, role = "STUDENT")
    }
}

@Composable
fun ParticipantRow(
    viewModel: UserViewModel,
    role: String,
) {
    viewModel.participants.value.forEach {
        participant ->
        if(participant.role == role){
            Row(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!participant.profilePicture.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(participant.profilePicture),
                        contentDescription = "Imagem do perfil",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profileimage),
                        contentDescription = "Imagem do perfil",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )
                }
                Text(
                    text = participant.name,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                )
            }
        } else {
            println(participant)
        }
    }
}


@Composable
fun Header(role: String) {
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
        if(role == "Professores") {
            Image(
                painter = painterResource(id = R.drawable.professor_icon),
                contentDescription = "Icone Turma",
                modifier = Modifier
                    .height(21.dp)
                    .padding(end = 16.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.aluno_icon),
                contentDescription = "Icone Turma",
                modifier = Modifier
                    .height(27.dp)
                    .padding(end = 16.dp)
            )
        }

        Text(
            text = role,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

