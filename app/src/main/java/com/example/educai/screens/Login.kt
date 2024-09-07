package com.example.educai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.educai.components.LoginContainer
import com.example.educai.ui.theme.MediumPurple

@Composable
fun Login(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .background(MediumPurple)
            .fillMaxSize()
    ) {
        Text(text = "Login Screen")
        LoginContainer()
    }
}