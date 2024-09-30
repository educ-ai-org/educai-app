package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun CardAtividade(name: String, endDate: LocalDate) {
    Row(
        modifier = Modifier
            .border(1.dp, MediumPurple, shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.book),
            contentDescription = "Book",
            modifier = Modifier
                .height(32.dp)
                .clip(RoundedCornerShape(2.dp))
        )

        Column(
            modifier = Modifier
                .padding(
                    start = 18.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardAtividadePreview() {
    CardAtividade(name = "Atividade 1", endDate = LocalDate.now())
}