package com.example.educai.data.network

import com.example.educai.MainActivity
import com.example.educai.data.contexts.TokenManager
import com.example.educai.data.services.AuthService
import com.example.educai.data.services.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://educai.eastus.cloudapp.azure.com/api/"

    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()

        if (!originalRequest.url().encodedPath().contains("/user/auth")) {
            val token = TokenManager.getAccessToken(MainActivity.context)
            if (token != null) {
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }
        }

        val request = requestBuilder.build()
        chain.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}
