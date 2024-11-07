package com.example.educai.screens.turma

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.educai.components.Post
import com.example.educai.data.viewmodel.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Posts(
    classroomId: String,
    postViewModel: PostViewModel = viewModel()
) {
    // Observe the posts from the ViewModel
    val posts by postViewModel.posts.observeAsState(emptyList())
    val error by postViewModel.error.observeAsState("")

    // Trigger the getPosts function when the composable is loaded
    LaunchedEffect(classroomId) {
        postViewModel.getPosts(classroomId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TurmaViwer()
        }

        // Display posts retrieved from ViewModel
        items(posts) { post ->
            Post(
                title = post.title,
                fileName = post.originalFileName,
                date = post.datePosting,
                description = post.description,
                fileUrl = post.file
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostsPreview() {
    Posts("classroomId")
}
