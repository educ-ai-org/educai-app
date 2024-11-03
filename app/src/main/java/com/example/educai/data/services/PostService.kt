package com.example.educai.data.services

import com.example.educai.data.model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("classroom/{classroomId}/posts")
    suspend fun getPostsByClassroom(@Path("classroomId") classroomId: String): List<Post>
}