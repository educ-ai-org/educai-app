package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.educai.R
import com.example.educai.ui.theme.ButtonPurple
import com.example.educai.ui.theme.LightGrey
import com.example.educai.ui.theme.Lilac
import com.example.educai.ui.theme.MediumGrey
import com.example.educai.ui.theme.MediumLilac
import com.example.educai.ui.theme.MediumPurple
import com.example.educai.ui.theme.TextColor
import com.example.educai.ui.theme.montserratFontFamily

@Composable
fun DictionaryModal(showModal: Boolean, onDismiss: () -> Unit) {

    if (!showModal) return
    var searchedWord by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(22.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Lilac)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dicionario_preto_icone),
                            contentDescription = "Dicionário",
                            modifier = Modifier
                                .size(22.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Text(
                        text = "Dicionário",
                        color = TextColor,
                        modifier = Modifier.fillMaxWidth(0.9f),
                        fontFamily = montserratFontFamily,
                        fontSize = 16.5.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Lilac, MediumLilac, Lilac)
                            )
                        )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    OutlinedTextField(
                        value = searchedWord,
                        onValueChange = { searchedWord = it },
//                        label = { Text("Ex: Word", color = MediumGrey, fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LightGrey,
                            unfocusedBorderColor = LightGrey,
                            focusedTextColor = MediumGrey
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.58f)
                            .height(50.dp),
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions.Default,
                        textStyle = TextStyle(fontSize = 14.sp)
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonPurple,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .height(50.dp)
                    ) {
                        Text(text = "Buscar")
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                // Search Result
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "Test",
                        color = TextColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = montserratFontFamily
                    )

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(33.dp)
                            .padding(start = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.audio_icone),
                            contentDescription = "Volume",
                            tint = MediumPurple,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "a way of discovering,by questions or practical activities.",
                    fontSize = 14.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold)

            }
        }
    }

}

@Preview
@Composable
fun DictionaryModalPreview() {
    DictionaryModal(showModal = true, onDismiss = {})
}