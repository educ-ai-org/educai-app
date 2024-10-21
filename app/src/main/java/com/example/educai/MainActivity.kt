package com.example.educai

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.educai.data.contexts.TokenManager
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.viewmodel.AuthViewModel
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
fun App(viewModel: AuthViewModel = viewModel()) {

    val backgroundGradient: Brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF6730EC),
            Color(0xFF1E0132),
        )
    )

    val refreshTokenResponse by viewModel.refreshTokenResponse.observeAsState()
    val errorRefreshToken by viewModel.errorRefreshToken.observeAsState()

    val navController = rememberNavController()

    var isUserLoggedIn by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    setNavigationAndStatusBarColors(isLoading)

    LaunchedEffect(Unit) {
        val refreshToken = TokenManager.getRefreshToken(MainActivity.context);

        if(refreshToken != null) {
            viewModel.renewToken(refreshToken)
        } else {
            isLoading = false
            isUserLoggedIn = false
        }
    }

    refreshTokenResponse?.let {
        val refreshToken = TokenManager.getRefreshToken(LocalContext.current);

        if (refreshToken != null) {
            TokenManager.saveTokens(
                context = LocalContext.current,
                accessToken = it.token,
                refreshToken = refreshToken
            )

            isLoading = false
            isUserLoggedIn = true
        } else {
            isLoading = false
            isUserLoggedIn = false
        }
    }

    errorRefreshToken?.let {
        isLoading = false
        isUserLoggedIn = false
    }

    if(isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.educai_logo),
                contentDescription = "Logo da Educ.AI Black",
                modifier = Modifier
                    .height(185.dp)
            )
        }
    } else {
        NavHost(
            navController = navController,
            startDestination = if (isUserLoggedIn) "home" else "login"
        ) {
            composable("login") {
                Login {
                    isUserLoggedIn = true
                    navController.navigate("home")
                }
            }
            composable("home") {
                MainUI()
            }
        }
    }
}

@Composable
fun setNavigationAndStatusBarColors(isLoading: Boolean) {
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = false
    )

    systemUiController.setNavigationBarColor(
        color = if(isLoading) {
            Color.Transparent
        } else {
            BackgroundColor
        }
    )
}
