package com.example.educai.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.educai.R
import com.example.educai.routes.DrawerScreens
import com.example.educai.routes.DrawerScreensList
import com.example.educai.ui.theme.montserratFontFamily

@Composable
fun DrawerMenu(
    currentRoute: String?,
    menuItemOnClick: (route: String) -> Unit,
    logoff: () -> Unit
) {
    val dividerGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE0D5F4),
            Color(0xFFA578F9),
            Color(0xFFDBCFF2)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 48.dp,
                    bottom = 12.dp,
                    start = 24.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.educai_logo_black),
                contentDescription = "Logo da Educ.AI Black",
                modifier = Modifier
                    .height(32.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(

            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .background(
                            dividerGradient
                        ),
                    thickness = 2.dp
                )

                DrawerScreensList.forEach { screen ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = "${screen.title} icon",
                                tint = screen.textColor,
                                modifier = Modifier
                                    .size(28.dp)
                            )
                        },
                        label = {
                            Text(
                                fontWeight = FontWeight.Bold,
                                text = screen.title,
                                color = screen.textColor
                            )
                        },
                        selected = screen.route === currentRoute,
                        onClick = {
                            menuItemOnClick(screen.route)
                        },
                        modifier = Modifier
                            .padding(
                                top = 12.dp
                            ),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0xFFF1EBFF),
                            unselectedContainerColor = Color.Transparent
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        logoff()
                    }
                    .padding(
                        all = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_logout),
                    contentDescription = "Logout button",
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.Red
                )
                Text(
                    text = "Sair",
                    modifier = Modifier
                        .padding(
                            start = 12.dp
                        ),
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuPreview() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        DrawerMenu(
            currentRoute = DrawerScreens.Home.route,
            menuItemOnClick = {

            },
            logoff = {

            }
        )
    }
}