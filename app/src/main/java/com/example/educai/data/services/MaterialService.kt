package com.example.educai.data.services

import com.example.educai.data.model.Classwork
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MaterialService {
    @Multipart
    @POST("generate-educational-resource")
    fun generateEducationalMaterial(
        @Part("instructions") instructions: String?,
        @Part("youtubeLink") youtubeLink: String?,
        @Part audio: MultipartBody.Part?,
        @Part document: MultipartBody.Part?,
        @Query("openai") openai: Boolean
    ): Call<ResponseBody>
}