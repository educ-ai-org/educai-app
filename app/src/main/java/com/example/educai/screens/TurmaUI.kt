package com.example.educai.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
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
fun TurmaUI(id: String) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(all = 0.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .shadow(16.dp),
                containerColor = TabBarColor,
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(0.dp)
                .background(BackgroundColor)
        ) {
            BottomScreensNavigation(navController = navController, id)
        }
    }
}