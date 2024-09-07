package com.example.educai.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.educai.R
import com.example.educai.components.DictionaryModal
import com.example.educai.components.DrawerMenu
import com.example.educai.components.TopBar
import com.example.educai.routes.DrawerScreens
import com.example.educai.routes.DrawerScreensNavigation
import com.example.educai.ui.theme.BackgroundColor
import com.example.educai.ui.theme.MediumPurple
import kotlinx.coroutines.launch

@Composable
fun MainUI() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: DrawerScreens.Home.route
    val scope = rememberCoroutineScope()
    var showModal by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(.6f)
            ) {
                DrawerMenu(
                    currentRoute = currentRoute,
                    menuItemOnClick = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    menuOpenEvent = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            floatingActionButton = {
                if (currentRoute == "turmaExemplo" || currentRoute == "home") {
                    FloatingActionButton(
                        onClick = {
                            showModal = true
                        },
                        modifier = Modifier
                            .padding(bottom = if (currentRoute == "turmaExemplo") 90.dp else 10.dp),
                        containerColor = MediumPurple,
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dicionario_icone),
                            contentDescription = "Dicionário",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(25.dp)
                        )
                    }
                }
            }
        ) { contentPadding ->
            Box(modifier = Modifier
                .background(BackgroundColor)
                .padding(contentPadding)
                .fillMaxSize()
            ) {
                DrawerScreensNavigation(navController = navController)
            }
            DictionaryModal(
                showModal = showModal,
                onDismiss = { showModal = false }
            )
        }
    }
}