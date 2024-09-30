package com.example.educai.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.R
import com.example.educai.ui.theme.LightGrey
import com.example.educai.ui.theme.MediumPurple

@Composable
fun Post() {
    val mockTitle = "Título Post"
    val mockDate = "01/09/2024"
    val mockDescription = "Texto descritivo do professor Texto descritivo do professor Texto descritivo"
    val mockFileName = "arquivo.pdf"

    val borderShape = RoundedCornerShape(10.dp)
    val roundedJustOnTop = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    val borderStroke = BorderStroke(2.dp, LightGrey)

    val fonteTitulo = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
    val fonteDescricao = TextStyle(fontSize = 16.sp, color = Color.Gray)
    val fonteLink = TextStyle(
        fontSize = 16.sp,
        color = MediumPurple,
        textDecoration = TextDecoration.Underline
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(borderStroke, borderShape)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(borderStroke, roundedJustOnTop)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.purple_book_icon),
                            contentDescription = "Ícone de livro",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = mockTitle,
                            style = fonteTitulo
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Data de publicação: $mockDate",
                    style = fonteDescricao
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mockDescription,
                    style = fonteDescricao
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = mockFileName,
                    style = fonteLink,
                    modifier = Modifier.clickable { /* Chamar função para abrir o arquivo */ }
                )
            }
        }
    }
}


@Composable
@Preview
fun PostPreview() {
    Post()
}