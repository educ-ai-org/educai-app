package com.example.educai.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.educai.R

sealed class BottomNavigationScreens(
    val title: String,
    val route: String,
    val icon: Int
) {
    data object Posts: BottomNavigationScreens(
        title = "Posts",
        route = "posts",
        icon =  R.drawable.icon_post
    )
    data object Atividades: BottomNavigationScreens(
        title = "Atividades",
        route = "atividades",
        icon = R.drawable.icon_atividades
    )
    data object Pessoas: BottomNavigationScreens(
        title = "Pessoas",
        route = "pessoas",
        icon = R.drawable.icon_peoples
    )
    data object Leaderboard: BottomNavigationScreens(
        title = "Leaderboard",
        route = "leaderboard",
        icon = R.drawable.icon_ranking
    )
}

val BottomNavigationScreensList = listOf(
    BottomNavigationScreens.Posts,
    BottomNavigationScreens.Atividades,
    BottomNavigationScreens.Leaderboard,
    BottomNavigationScreens.Pessoas,
)