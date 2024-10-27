package com.example.educai.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.educai.R
import com.example.educai.ui.theme.MediumPurple
import com.example.educai.ui.theme.TextColor

sealed class DrawerScreens (
    val title: String,
    val route: String,
    val icon: Int,
    val textColor: Color
) {
    data object Home: DrawerScreens(
        title = "Início",
        route = "home",
        icon = R.drawable.icon_home,
        textColor = TextColor
    )
    data object DetalhesTurma: DrawerScreens(
        title = "Turma de Inglês",
        route = "turmaDetalhes/{turmaId}",
        icon = R.drawable.icon_classroom,
        textColor = TextColor
    ) {
        fun createRoute(turmaId: String) = "turmaDetalhes/$turmaId"
    }
    data object CriarMaterial: DrawerScreens(
        title = "Criar Material",
        route = "criarMaterial",
        icon = R.drawable.icon_material,
        textColor = MediumPurple
    )
    data object FaleComEdu: DrawerScreens(
        title = "Fale com Edu",
        route = "faleComEdu",
        icon = R.drawable.icon_chat_edu,
        textColor = MediumPurple
    )
}

val DrawerScreensList = listOf(
    DrawerScreens.Home,
    DrawerScreens.CriarMaterial,
    DrawerScreens.FaleComEdu,
)