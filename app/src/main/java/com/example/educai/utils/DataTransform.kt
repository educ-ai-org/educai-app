package com.example.educai.utils

import com.example.educai.data.model.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun String.getErrorMessageFromJson(): ErrorResponse? {
    val gson = Gson()
    return try {
        gson.fromJson(this, ErrorResponse::class.java)
    } catch (e: Exception) {
        null
    }
}

inline fun <reified T> String.getObjectFromJson(): T? {
    val gson = Gson()
    return try {
        val type = object : TypeToken<T>() {}.type
        gson.fromJson<T>(this, type)
    } catch (e: Exception) {
        null
    }
}
