package com.example.educai.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.educai.data.contexts.TokenManager
import com.example.educai.data.model.LoginRequest
import com.example.educai.data.viewmodel.AuthViewModel

@Composable
fun LoginForm(
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val errorMessage by viewModel.errorMessage.observeAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }
    val loginResponse by viewModel.loginResponse.observeAsState()
    val context = LocalContext.current

    var errorMessageText by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    loginResponse?.let {
        onLoginSuccess()
        TokenManager.saveTokens(
            context = context,
            accessToken = loginResponse!!.token,
            refreshToken = loginResponse!!.refreshToken
        )
        isLoading = true
    }

    errorMessage?.let {
        if(errorMessage!!.message == "Access Denied" && errorMessage!!.status == 403) {
            errorMessageText = "Email ou senha inválidos!"
        } else {
            errorMessageText = "Ocorreu um erro, tente novamente mais tarde!"
        }

        isLoading = false
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Senha") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        if(errorMessageText.isNotBlank()) {
            Text(
                text = errorMessageText,
                modifier = Modifier
                    .padding(start = 16.dp),
                color = Color.Red
            )
        }

        DefaultButton(
            modifier = Modifier
                .padding(16.dp),
            isLoading = isLoading,
            onClick = {
                isLoading = true
                viewModel.login(LoginRequest(email, password)) },
            text = "Login"
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        ) {
            Text(
                text = "Esqueceu a senha?",
                modifier = Modifier.padding(0.dp),
                color = Color.Gray,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
@Preview
fun LoginFormPreview() {
    LoginForm({})
}