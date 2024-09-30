package com.example.educai.data.services

import com.example.educai.data.model.Classroom
import com.example.educai.data.model.LoginRequest
import com.example.educai.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface UserService {
    @GET("user/classrooms")
    fun getUserClassrooms(): Call<List<Classroom>>
}