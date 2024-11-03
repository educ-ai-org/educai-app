package com.example.educai.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educai.data.model.Post
import com.example.educai.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPosts(classroomId: String) {
        viewModelScope.launch {
            try {
                val postsList = RetrofitInstance.postsService.getPostsByClassroom(classroomId)
                _posts.postValue(postsList)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Erro desconhecido")
            }
        }
    }
}