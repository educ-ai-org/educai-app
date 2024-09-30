package com.example.educai.screens.turma

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.ui.theme.LightPurple
import com.example.educai.R
import com.example.educai.components.StudentRanking
import com.example.educai.data.viewmodel.LeaderboardViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Leaderboard(idTurma: String,
    viewModel: LeaderboardViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getLeaderboard(idTurma)
        Log.d("Leaderboard", "Leaderboard data: ${viewModel.leaderboard.value}")
    }

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

        LazyColumn(
            modifier = Modifier
                .background(
                    color = LightPurple,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            itemsIndexed(viewModel.leaderboard.value) { index, student ->
                StudentRanking(
                    posicao = index + 1,
                    nome = student.name,
                    pontos = student.score
                )
            }
        }

    }
}

@Preview
@Composable
fun LeaderboardPreview() {
    Leaderboard("dasdjashd")
}
