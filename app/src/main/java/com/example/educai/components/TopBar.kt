package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.educai.R
import com.example.educai.data.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.educai.ui.theme.DarkPurple
import com.example.educai.ui.theme.LightPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(menuOpenEvent: () -> Unit) {
    val gradient = Brush.linearGradient(
        colors = listOf(LightPurple, DarkPurple)
    )

    TopAppBar(
        title = {
            TopBarContent()
        },
        navigationIcon = {
            IconButton(onClick = {
                menuOpenEvent()
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        ),
        modifier = Modifier
            .shadow(16.dp, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(
                brush = gradient,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                ),
            )
    )
}

@Composable
fun TopBarContent(viewModel: UserViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getUserPictureUrl()
    }

    val profilePictureUrl by viewModel.userPictureUrl.observeAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = 58.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.educai_logo),
            contentDescription = "Logo da Educ.AI",
            modifier = Modifier
                .height(32.dp)
        )
    }

    Row(
        modifier = Modifier
            .padding(
                end = 12.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        if (profilePictureUrl != null) {
            Image(
                painter = rememberAsyncImagePainter(profilePictureUrl),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.profileimage),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .height(38.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
        }

    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(menuOpenEvent = {})
}
