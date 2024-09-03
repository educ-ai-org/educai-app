package com.example.educai.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { Text(text = "Email") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { Text(text = "Senha") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = "Login")
        }
        Column(
            verticalArrangement = Arrangement.Top, // Garante que os elementos estão alinhados no topo sem gaps
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        ) {
            Text(
                text = "Esqueceu a senha?",
                modifier = Modifier.padding(0.dp), // Remove padding do Text
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Login com contas sociais",
                modifier = Modifier.padding(0.dp), // Remove padding do Text
                color = Color.Gray,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
@Preview
fun LoginFormPreview() {
    LoginForm()
}