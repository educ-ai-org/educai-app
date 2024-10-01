package com.example.educai.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educai.routes.BottomNavigationScreens
import com.example.educai.routes.BottomNavigationScreensList
import com.example.educai.ui.theme.MediumPurple
import com.example.educai.ui.theme.TabBarColor
import com.example.educai.ui.theme.TextColor
import com.example.educai.ui.theme.montserratFontFamily

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(BottomNavigationScreens.Posts.route) }

    BottomNavigationScreensList.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar(
        containerColor = TabBarColor,
        contentColor = TabBarColor,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        BottomNavigationScreensList.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.height(24.dp)) },
                label = {
                    Text(
                        item.title,
                        fontFamily = montserratFontFamily,
                        fontSize = 11.5.sp
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = TextColor,
                    unselectedTextColor = TextColor,
                    selectedIconColor = MediumPurple,
                    selectedTextColor = MediumPurple
                )
            )
        }
    }
}