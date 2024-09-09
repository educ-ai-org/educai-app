package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.R

@Composable
fun LoginContainer(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                clip = false
            )
            .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
    ) {
        Column(
            modifier = Modifier.padding(24.dp, top= 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.educai_logo_black),
                contentDescription = "Logo da Educ.AI Black",
                modifier = Modifier
                    .height(48.dp)
            )
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Faça login para continuar com sua conta",
                color = Color.Gray
            )
        }
        LoginForm(onLoginSuccess)
    }
}

@Preview(backgroundColor = 0xFF6730EC)
@Composable
fun LoginContainerPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoginContainer({})
    }
}