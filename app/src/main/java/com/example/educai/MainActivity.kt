package com.example.educai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.educai.screens.Login
import com.example.educai.screens.MainUI
import com.example.educai.ui.theme.EducAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EducAITheme {
                App()
            }
        }
    }
}

@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    val isUserLoggedIn = remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn.value) "home" else "login"
    ) {
        composable("login") {
            Login {
                isUserLoggedIn.value = true
                navController.navigate("home")
            }
        }
        composable("home") {
            MainUI()
        }
    }
}
