package com.example.educai.data.services

import com.example.educai.data.model.LoginRequest
import com.example.educai.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user/auth")
    fun login(@Body credentials: LoginRequest): Call<LoginResponse>
}