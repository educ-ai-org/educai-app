package com.example.educai

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.educai.data.model.ErrorResponse
import com.example.educai.screens.Login
import com.example.educai.screens.MainUI
import com.example.educai.ui.theme.BackgroundColor
import com.example.educai.ui.theme.EducAITheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = applicationContext
        enableEdgeToEdge()
        setContent {
            EducAITheme(
                dynamicColor = false
            ) {
                App()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }
}

@Preview
@Composable
fun App() {
    setNavigationAndStatusBarColors()

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

@Composable
fun setNavigationAndStatusBarColors() {
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = false
    )

    systemUiController.setNavigationBarColor(
        color = BackgroundColor
    )
}
