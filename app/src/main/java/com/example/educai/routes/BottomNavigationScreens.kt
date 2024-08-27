package com.example.educai.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens (
    val title: String,
    val route: String,
    val icon: ImageVector
) {
    data object Posts: BottomNavigationScreens(
        title = "Posts",
        route = "posts",
        icon = Icons.AutoMirrored.Outlined.Send
    )
    data object Atividades: BottomNavigationScreens(
        title = "Atividades",
        route = "atividades",
        icon = Icons.AutoMirrored.Filled.List
    )
    data object Pessoas: BottomNavigationScreens(
        title = "Pessoas",
        route = "pessoas",
        icon = Icons.Default.AccountCircle
    )
    data object Leaderboard: BottomNavigationScreens(
        title = "Leaderboard",
        route = "leaderboard",
        icon = Icons.Default.KeyboardArrowUp
    )
}

val BottomNavigationScreensList = listOf(
    BottomNavigationScreens.Posts,
    BottomNavigationScreens.Atividades,
    BottomNavigationScreens.Leaderboard,
    BottomNavigationScreens.Pessoas,
)