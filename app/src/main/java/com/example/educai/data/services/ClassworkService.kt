package com.example.educai.data.services

import com.example.educai.data.model.AnsweredClasswork
import retrofit2.Call
import com.example.educai.data.model.Classwork
import com.example.educai.data.model.ClassworkReview
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ClassworkService {
    @GET("classwork/{classworkId}")
    fun getClassworkById(@Path("classworkId") classworkId: String): Call<Classwork>

    @POST("classwork/answer")
    fun sendClasswork(
        @Header("classworkId") classworkId: String,
        @Body classwork: AnsweredClasswork
    ): Call<Void>

    @GET("classwork/{classworkId}/answer")
    fun getClassworkReview(@Path("classworkId") classworkId: String): Call<ClassworkReview>
}