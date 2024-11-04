package com.example.educai.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log the request details
        println("Sending request: ${request.url}")
        println("Request method: ${request.method}")
        println("Request headers: ${request.headers}")

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: IOException) {
            println("Request failed: ${e.message}")
            throw e
        }

        // Log the response details
        println("Received response for: ${response.request.url}")
        println("Response status: ${response.code}")
        println("Response message: ${response.message}")

        // Clone the response body to log it without consuming it
        val responseBody = response.peekBody(Long.MAX_VALUE)
        println("Response body: ${responseBody.string()}")

        return response
    }
}