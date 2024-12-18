package com.example.educai.data.services

import com.example.educai.data.model.Classroom
import com.example.educai.data.model.Participant
import com.example.educai.data.model.StudentPicture
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("user/classrooms")
    fun getUserClassrooms(): Call<List<Classroom>>

    @GET("classroom/{classroomId}/participants")
    suspend fun getParticipantsByClassId(@Path("classroomId") classroomId: String): Response<List<Participant>>

    @GET("user/picture-url")
    suspend fun getUserPictureUrl(): Response<String>

    @GET("classroom/{classroomId}/profile-pictures")
    suspend fun getProfilePictures(@Path("classroomId") classroomId: String): Response<List<StudentPicture>>

}