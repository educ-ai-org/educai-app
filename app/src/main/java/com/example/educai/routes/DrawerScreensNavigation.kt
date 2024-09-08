package com.example.educai.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
        composable(DrawerScreens.TurmasExemplo.route) {
            TurmaUI()
        }
    }
}