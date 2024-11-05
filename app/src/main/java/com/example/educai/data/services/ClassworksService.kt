package com.example.educai.data.services

import com.example.educai.data.model.Classwork
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ClassworksService {
    @GET("classroom/{id}/studentClassworks")
    fun getClassworks(@Path("id") id: String) : Call<List<Classwork>>
}