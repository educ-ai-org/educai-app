package com.example.educai.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.educai.ui.theme.MediumPurple
import com.example.educai.ui.theme.TextColor

sealed class DrawerScreens (
    val title: String,
    val route: String,
    val icon: ImageVector,
    val textColor: Color
) {
    data object Home: DrawerScreens(
        title = "Início",
        route = "home",
        icon = Icons.Outlined.Home,
        textColor = TextColor
    )
    data object TurmasExemplo: DrawerScreens(
        title = "Turma de Inglês",
        route = "turmaExemplo",
        icon = Icons.AutoMirrored.Filled.List,
        textColor = TextColor
    )
    data object CriarMaterial: DrawerScreens(
        title = "Criar Material",
        route = "criarMaterial",
        icon = Icons.Outlined.Edit,
        textColor = MediumPurple
    )
    data object FaleComEdu: DrawerScreens(
        title = "Fale com Edu",
        route = "faleComEdu",
        icon = Icons.Default.Call,
        textColor = MediumPurple
    )
}

val DrawerScreensList = listOf(
    DrawerScreens.Home,
    DrawerScreens.CriarMaterial,
    DrawerScreens.FaleComEdu,
)