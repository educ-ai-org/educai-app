package com.example.educai.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.educai.R
import com.example.educai.components.LoginContainer
import com.example.educai.ui.theme.MediumPurple

@Composable
fun Login(
    onLoginSuccess: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MediumPurple)
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Foto do login"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
        ) {
            LoginContainer(onLoginSuccess)
        }
    }
}

@Composable
@Preview
fun LoginPreview() {
    Login {}
}