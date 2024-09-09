package com.example.educai.screens.turma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.educai.mock.getMockAtividades
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
fun Atividades() {
    var atividades by remember { mutableStateOf(listOf<AtividadeData>()) }

    LaunchedEffect(Unit) {
        atividades = fetchAtividadesFromDatabase()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Atividades")
    }
}