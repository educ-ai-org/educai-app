package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.R

@Composable
fun Class(nomeTurma:String, nomeDisciplina:String, quantidadeAlunos:Int, onClick: () -> Unit) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(bottom = 16.dp)
            .background(
                Color.White,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
            )
            .border(
                1.dp,
                Color.LightGray,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
    ) {
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
        ) {
            Row {
                Column {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.class_icon),
                            contentDescription = "Pessoas agrupadas",
                            modifier = Modifier
                                .padding(start = 16.dp)
                        )
                        Text(
                            text = nomeTurma,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp),
                            color = Color.Black
                        )
                    }

                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }


            Row {
                Text(
                    text = "Nome da disciplina: ",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = nomeDisciplina,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
            Row {
                Text(
                    text = "Quantidade de alunos: ",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = quantidadeAlunos.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }
    }
}