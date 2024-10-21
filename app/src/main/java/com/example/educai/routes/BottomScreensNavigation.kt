package com.example.educai.routes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.educai.screens.turma.Atividades
import com.example.educai.screens.turma.Leaderboard
import com.example.educai.screens.turma.Pessoas
import com.example.educai.screens.turma.Posts

@Composable
fun BottomScreensNavigation(navController: NavHostController, idTurma: String) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Posts.route
    ) {
        composable(BottomNavigationScreens.Posts.route) {
            Posts()
        }
        composable(BottomNavigationScreens.Atividades.route) {
            Atividades(idTurma)
        }
        composable(BottomNavigationScreens.Pessoas.route) {
            Pessoas(classRoomId = idTurma)
        }
        composable(BottomNavigationScreens.Leaderboard.route) {
            Leaderboard(idTurma)
            Log.d("Leaderboard", "idTurma recebido: $idTurma")
        }
    }
}