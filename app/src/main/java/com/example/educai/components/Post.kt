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
    val mockDescription = "Texto descritivo do professot Texto descritivo do professot Texto descritivo"
    val mockFileName = "arquivo.pdf"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(BorderStroke(1.dp, LightGrey), shape = RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(BorderStroke(1.dp, LightGrey), shape = RoundedCornerShape(10.dp))
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
                            fontSize = 21.sp,
                            fontWeight = FontWeight.SemiBold
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
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mockDescription,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = mockFileName,
                    fontSize = 16.sp,
                    color = MediumPurple,
                    modifier = Modifier.clickable { /* Chamar função para abrir o arquivo */ },
                    style = TextStyle(textDecoration = TextDecoration.Underline)
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