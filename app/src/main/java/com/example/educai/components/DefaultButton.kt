package com.example.educai.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.ui.theme.MediumPurple
import com.example.educai.ui.theme.montserratFontFamily

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        enabled = !isLoading,
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MediumPurple
        )
    ) {
        if(isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(
                        top = 6.dp
                    )
                    .width(24.dp),
                color = Color.White,
                trackColor = Color.Transparent,
            )
        } else {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = montserratFontFamily
            )
        }
    }
}
