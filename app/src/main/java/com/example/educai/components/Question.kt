package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.R
import com.example.educai.data.model.AnsweredQuestion
import com.example.educai.data.model.Question
import com.example.educai.ui.theme.LightGreen
import com.example.educai.ui.theme.LightGrey
import com.example.educai.ui.theme.LightRed
import com.example.educai.ui.theme.MediumGrey
import com.example.educai.ui.theme.MediumPurple

@Composable
fun Question(
    question: Question,
    index: Int,
    changeQuestionOption: ((AnsweredQuestion) -> Unit)? = null,
    studentAnswer: AnsweredQuestion? = null
) {
    val name = "Questão ${index + 1}"

    var selectedOption by remember {
        mutableIntStateOf(-1)
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
                text = question.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(
                        vertical = 6.dp
                    )
            )
        }

        question.options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .border(1.dp, Color(0xFFEDEDED), RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(
                        color =
                        if (option.key == question.correctAnswerKey && studentAnswer != null) {
                            LightGreen
                        } else if (studentAnswer?.optionKey == option.key) {
                            LightRed
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(
                            if(studentAnswer != null) {
                                .85f
                            } else {
                                1f
                            }
                        )
                        .padding(
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = if (studentAnswer == null) {
                            selectedOption == index
                        } else {
                            option.key == studentAnswer.optionKey
                        },
                        onClick = {
                            selectedOption = index
                            if (changeQuestionOption != null) {
                                changeQuestionOption(AnsweredQuestion(
                                    optionKey = option.key,
                                    questionId = question.id
                                ))
                            }
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = if(studentAnswer == null) {
                                MediumPurple
                            } else {
                                MediumGrey
                            },
                        ),
                        enabled = studentAnswer == null
                    )
                    Text(text = option.description)
                }

                if (option.key == question.correctAnswerKey) {
                    Image(
                        painter = painterResource(id = R.drawable.correct),
                        contentDescription = "Icone de acerto",
                        modifier = Modifier
                            .height(18.dp)
                            .padding(end = 16.dp)
                    )
                } else if (studentAnswer?.optionKey == option.key) {
                    Image(
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = "Icone de erro",
                        modifier = Modifier
                            .height(18.dp)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionPreview() {
//    Question()
}

data class Options(
    val description: String,
    var checked: Boolean = false
) {
    fun enable() {
        checked = true
    }
}