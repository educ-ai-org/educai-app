package com.example.educai.routes

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.educai.screens.FaleComEdu
import com.example.educai.screens.Home
import com.example.educai.screens.MaterialCreation
import com.example.educai.screens.TurmaUI

@Composable
fun DrawerScreensNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DrawerScreens.Home.route
    ) {
        composable(DrawerScreens.Home.route) {
            Home(navController = navController)
        }
        composable(DrawerScreens.CriarMaterial.route) {
            MaterialCreation()
        }
        composable(DrawerScreens.FaleComEdu.route) {
            FaleComEdu()
        }

        composable(
            route = DrawerScreens.DetalhesTurma.route,
            arguments = listOf(navArgument("turmaId") { type = NavType.StringType })
        ) { backStackEntry ->
            val turmaId = backStackEntry.arguments?.getString("turmaId")
            Log.d("TurmaDetalhes", "turmaId recebido: $turmaId")
            if (turmaId != null) {
                TurmaUI(turmaId)
            } else {
                Text(text = "Erro: ID da turma não encontrado.")
            }
        }
    }
}