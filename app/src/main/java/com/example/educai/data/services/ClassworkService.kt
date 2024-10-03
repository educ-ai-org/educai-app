package com.example.educai.data.services

import retrofit2.Call
import com.example.educai.data.model.Classwork
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ClassworkService {
    @GET("classwork/{classworkId}")
    fun getClassworkById(@Path("classworkId") classworkId: String): Call<Classwork>

    @POST("/classwork/answer")
    fun sendClasswork(
        @Header("userId") userId: String,
        @Header("classworkId") classworkId: String
    ): Call<Void>
}