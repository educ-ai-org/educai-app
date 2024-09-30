package com.example.educai.data.services

import com.example.educai.data.model.WordDefinition
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryService {
    @GET("dictionary/{word}/definition")
    fun getWordDefinition(@Path("word") word: String): Call<WordDefinition>
}