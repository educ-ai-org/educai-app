package com.example.educai.screens.turma

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.educai.components.Post

@Composable
fun Posts() {
    val postList = listOf(
        "Post 1",
        "Post 2",
        "Post 3",
        "Post 4",
        "Post 5",
        "Post 6"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TurmaViwer()
        }

        items(postList) { post ->
            Post()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostsPreview() {
    Posts()
}
