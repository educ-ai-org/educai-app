package com.example.educai.data.services

import com.example.educai.data.model.Classroom
import com.example.educai.data.model.LoginRequest
import com.example.educai.data.model.LoginResponse
import com.example.educai.data.model.Participant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("user/classrooms")
    fun getUserClassrooms(): Call<List<Classroom>>

    @GET("classroom/{classroomId}/participants")
    fun getParticipantsByClassId(@Path("classroomId") classroomId: String): Call<List<Participant>>

    @GET("user/picture-url")
    suspend fun getUserPictureUrl(): Response<String>

}