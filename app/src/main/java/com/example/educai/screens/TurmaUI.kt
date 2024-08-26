package com.example.educai.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.educai.components.BottomNavigationBar
import com.example.educai.routes.BottomScreensNavigation
import com.example.educai.ui.theme.BackgroundColor
import com.example.educai.ui.theme.TabBarColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TurmaUI() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .padding(0.dp)
                    .shadow(16.dp),
                containerColor = TabBarColor,
            ) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(BackgroundColor)
        ) {
            BottomScreensNavigation(navController = navController)
        }
    }
}