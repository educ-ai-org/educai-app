package com.example.educai.data.services

import com.example.educai.data.model.EduRequest
import com.example.educai.data.model.EduResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IAService {
    @POST("edu-response")
    fun getEduResponse(@Body eduRequest: EduRequest): Call<EduResponse>
}