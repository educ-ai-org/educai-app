package com.example.educai.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.ui.theme.BackgroundColor
import com.example.educai.R

@Composable
fun FaleComEdu() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        EduProfile()
    }
}

@Preview
@Composable
fun FaleComEduPreview() {
    FaleComEdu()
}

@Composable
fun EduProfile() {
    Row(
        modifier = Modifier
            .border(1.dp, Color(0xFFD4D4D4), shape = RoundedCornerShape(10.dp))
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.robot),
            contentDescription = "Foto do Edu",
            modifier = Modifier
                .height(38.dp)
        )

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chat Edu",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                OnlineStatusDot()
                Text(
                    text = "Online",
                    fontSize = 10.sp,
                    color = Color(0xFF00B115),
                    modifier = Modifier
                        .padding(
                            start = 4.dp
                        )
                )
            }
        }
    }
}

@Composable
fun OnlineStatusDot() {
    val scale by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(10.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .border(
                .5.dp, Color(0xFF00B115),
                shape = RoundedCornerShape(50)
            )
            .background(Color.Transparent, shape = CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .padding(2.dp)
                .background(Color(0xFF00B115), shape = CircleShape)
        )
    }
}