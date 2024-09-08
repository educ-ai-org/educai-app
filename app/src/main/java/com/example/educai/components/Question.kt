package com.example.educai.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.ui.theme.LightGrey
import com.example.educai.ui.theme.MediumPurple

@Composable
fun Question() {
    val name = "Question 1"
    val description = "1 - O que é o verb to be?"
    val options: List<Options> = listOf<Options>(
        Options("Opção 1", false),
        Options("Opção 2", false),
        Options("Opção 3", false),
        Options("Opção 4", false),
    )

    var selectedOption by remember {
        mutableStateOf(-1)
    }

    Column(
        modifier = Modifier
            .border(1.dp, LightGrey, shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 12.dp
                )
        ) {
            Text(
                text = name,
                fontSize = 12.sp,
                color = Color(0xFFD1D1D1)
            )

            Text(
                text = description,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(
                        vertical = 6.dp
                    )
            )
        }

        options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .border(1.dp, Color(0xFFEDEDED), RoundedCornerShape(8.dp))
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == index,
                    onClick = { selectedOption = index },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MediumPurple
                    )
                )
                Text(text = option.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionPreview() {
    Question()
}

data class Options(
    val description: String,
    var checked: Boolean = false
) {
    fun enable() {
        checked = true
    }
}