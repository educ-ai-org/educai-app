package com.example.educai.utils

import com.example.educai.data.model.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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

fun String.toDate(): String {
    return try {
        LocalDate.parse(this).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
    } catch (e: Exception) {
        "-"
    }
}