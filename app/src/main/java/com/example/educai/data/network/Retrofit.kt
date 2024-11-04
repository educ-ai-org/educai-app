package com.example.educai.data.network

import com.example.educai.MainActivity
import com.example.educai.data.contexts.TokenManager
import com.example.educai.data.services.AuthService
import com.example.educai.data.services.ClassworkService
import com.example.educai.data.services.ClassworksService
import com.example.educai.data.services.LeaderboardService
import com.example.educai.data.services.IAService
import com.example.educai.data.services.DictionaryService
import com.example.educai.data.services.PostService
import com.example.educai.data.services.MaterialService
import com.example.educai.data.services.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://api.educai.xyz"
    private const val BASE_URL_IA = "https://ai.educai.xyz"

    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()

        if (!originalRequest.url.encodedPath.contains("/user/auth")) {
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
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val retrofitScalars: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val retrofitIA: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_IA)
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

    val userServiceScalars: UserService by lazy {
        retrofitScalars.create(UserService::class.java)
    }

    val leaderboardService: LeaderboardService by lazy {
        retrofit.create(LeaderboardService::class.java)
    }

    val iaService: IAService by lazy {
        retrofitIA.create(IAService::class.java)
    }
    
    val dictionaryService: DictionaryService by lazy {
        retrofit.create(DictionaryService::class.java)
    }

    val classworkService: ClassworkService by lazy {
        retrofit.create(ClassworkService::class.java)
    }

    val postsService: PostService by lazy {
        retrofit.create(PostService::class.java)
    }

    val classworksService: ClassworksService by lazy {
        retrofit.create(ClassworksService::class.java)
    }

    val materialService by lazy {
        retrofitIA.create(MaterialService::class.java)
    }

}

