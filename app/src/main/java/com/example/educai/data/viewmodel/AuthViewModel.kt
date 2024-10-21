package com.example.educai.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.MainActivity
import com.example.educai.data.contexts.TokenManager
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.LoginRequest
import com.example.educai.data.model.LoginResponse
import com.example.educai.data.model.RefreshTokenResponse
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    val loginResponse = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<ErrorResponse>()

    val refreshTokenResponse = MutableLiveData<RefreshTokenResponse>()
    val errorRefreshToken = MutableLiveData<ErrorResponse>()

    fun login(credentials: LoginRequest) {
        val call = RetrofitInstance.authService.login(credentials)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginResponse.postValue(response.body())
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
            }
        })
    }

    fun renewToken(token: String) {
        val call = RetrofitInstance.authService.refreshToken("refreshToken=$token")

        call.enqueue(object : Callback<RefreshTokenResponse> {
            override fun onResponse(call: Call<RefreshTokenResponse>, response: Response<RefreshTokenResponse>) {
                if (response.isSuccessful) {
                    refreshTokenResponse.postValue(response.body())
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
            }

            override fun onFailure(call: Call<RefreshTokenResponse>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
            }
        })
    }
}