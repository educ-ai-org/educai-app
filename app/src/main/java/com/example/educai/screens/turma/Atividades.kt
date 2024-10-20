package com.example.educai.screens.turma

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.educai.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.educai.components.Question
import com.example.educai.data.model.Classwork
import com.example.educai.data.model.Option
import com.example.educai.data.model.Question
import com.example.educai.data.viewmodel.ClassworksViewModel
import com.example.educai.data.viewmodel.LeaderboardViewModel
import com.example.educai.ui.theme.GrayBold
import com.example.educai.ui.theme.Green
import com.example.educai.ui.theme.LightPurple
import com.example.educai.ui.theme.Yellow

val fonte = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal)
)

val fonteBold = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Normal)
)

val fonteSemibold = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.Normal)
)

@Composable
fun Atividades(idTurma: String, viewModel: ClassworksViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ListaAtividades(idTurma = idTurma, viewModel = viewModel) { turmaId ->
                navController.navigate("atividade/$turmaId")
            }
        }
        composable("atividade") {
            com.example.educai.screens.turma.atividade.Atividade {
                navController.navigate("list")
            }
        }
    }
}

@Composable
fun ListaAtividades(viewModel: ClassworksViewModel = viewModel(), idTurma: String, navegarAtividade: (String) -> Unit) {
    val atividades by viewModel.classworks.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getClassworks(idTurma)
        Log.d("Classworks", "Classworks data: ${viewModel.classworks.value}")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
            TurmaViwer()
        }
        items(atividades.size) { index ->
            Box(
                modifier = Modifier.clickable { navegarAtividade(idTurma) }
            ) {
                Atividade(atividadeData = atividades[index])
            }
        }
    }
}

@Composable
fun Atividade(atividadeData: Classwork) {
    val fontePequena = MaterialTheme.typography.bodySmall.copy(fontFamily = fonte, color = GrayBold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    val fonteMedia = MaterialTheme.typography.bodyMedium.copy(fontFamily = fonte, color = GrayBold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    val fonteBoldTitulo = MaterialTheme.typography.titleMedium.copy(fontFamily = fonteBold, fontSize = 16.sp)
    val fonteBoldPequena = MaterialTheme.typography.bodySmall.copy(fontFamily = fonteSemibold, fontSize = 12.sp)

    var sentOrPending = if (atividadeData.hasAnswered == true) R.drawable.sent else R.drawable.pending
    var sentOrPendingSize = if (atividadeData.hasAnswered == true) 14.dp else 12.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = Color.White)
            .border(width = 2.dp, color = Color(0xFFBEBEBE), shape = MaterialTheme.shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val strokeWidth = 2.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color = Color(0xFFBEBEBE),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.atividade1),
                    contentDescription = "Icone caderninho",
                    modifier = Modifier
                        .weight(0.1f)
                        .size(24.dp)
                )
                Row(
                    modifier = Modifier.weight(0.9f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "${atividadeData.title}", style = fonteBoldTitulo)
                    Text(
                        text = buildAnnotatedString {
                            append("Prazo: ")
                            withStyle(style = SpanStyle(fontFamily = fonteBold)) {
                                append(atividadeData.endDate)
                            }
                        },
                        style = fonteMedia
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Data publicação: ")
                        withStyle(style = SpanStyle(color = GrayBold, fontFamily = fonteBold)) {
                            append(atividadeData.datePosting)
                        }
                    },
                    textAlign = TextAlign.Start,
                    style = fontePequena
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Status: ", style = fontePequena)
                    Text(
                        text = if (atividadeData.hasAnswered == true) "Enviado" else "Pendente",
                        color = if (atividadeData.hasAnswered == true) Green else Yellow,
                        style = fonteBoldPequena
                    )
                    Image(
                        painter = painterResource(id = sentOrPending),
                        contentDescription = null,
                        modifier = Modifier
                            .size(sentOrPendingSize)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            ) {
                Text(text = "${atividadeData.description}", style = fontePequena)
            }
        }
    }
}

@Composable
fun TurmaViwer(modifier: Modifier = Modifier) {

    val fonteBoldTitulo = MaterialTheme.typography.titleLarge.copy(fontFamily = fonteBold, fontSize = 18.sp)
    val fonteMedia = MaterialTheme.typography.bodyMedium.copy(fontFamily = fonteSemibold, fontSize = 14.sp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(
                width = 2.dp,
                color = LightPurple,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_turma),
                contentDescription = "Icone Turma",
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )
            Column(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Turma 01",
                    style = fonteBoldTitulo
                )
                Text(
                    text = "Inglês",
                    style = fonteMedia
                )
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(
                    text = "30 alunos",
                    style = fonteMedia
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTurmaViwer() {
    TurmaViwer()
}

@Preview(showBackground = true)
@Composable
fun PreviewAtividade() {
    Atividade(
        atividadeData = Classwork(
            id = "1",
            title = "Sample Activity",
            datePosting = "2023-10-01",
            endDate = "2023-10-10",
            description = "This is a sample activity description.",
            totalAnswers = 10,
            totalQuestions = 5,
            questions = listOf(
                Question(
                    id = "1",
                    description = "Sample Question 1",
                    correctAnswerKey = "A",
                    options = listOf(
                        Option(id = "1", key = "A", description = "Option 1"),
                        Option(id = "2", key = "B", description = "Option 2"),
                        Option(id = "3", key = "C", description = "Option 3"),
                        Option(id = "4", key = "D", description = "Option 4")
                    )
                ),
                Question(
                    id = "2",
                    description = "Sample Question 2",
                    correctAnswerKey = "B",
                    options = listOf(
                        Option(id = "1", key = "A", description = "Option 1"),
                        Option(id = "2", key = "B", description = "Option 2"),
                        Option(id = "3", key = "C", description = "Option 3"),
                        Option(id = "4", key = "D", description = "Option 4")
                    )
                )
            ),
            correctPercentage = 80.0,
            hasAnswered = true
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAtividades() {
    Atividades(idTurma = "1")
}