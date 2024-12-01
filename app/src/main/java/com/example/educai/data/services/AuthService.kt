package com.example.educai.data.services

import com.example.educai.data.model.LoginRequest
import com.example.educai.data.model.LoginResponse
import com.example.educai.data.model.RefreshTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("user/auth")
    fun login(@Body credentials: LoginRequest): Call<LoginResponse>

    @POST("user/refreshToken")
    fun refreshToken(
        @Header("Cookie") refreshToken: String
    ): Call<RefreshTokenResponse>

    @POST("user/logoff")
    fun logoff(): Call<Void>
}