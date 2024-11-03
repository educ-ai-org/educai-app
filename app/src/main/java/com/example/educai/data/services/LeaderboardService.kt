package com.example.educai.data.services

import retrofit2.Call
import com.example.educai.data.model.Leaderboard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LeaderboardService {
    @GET("classroom/{classroomId}/leaderboard")
    suspend fun getLeaderboard(@Path("classroomId") classroomId: String): Response<List<Leaderboard>>
}