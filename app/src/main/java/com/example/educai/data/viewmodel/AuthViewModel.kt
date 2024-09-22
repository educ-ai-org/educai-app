package com.example.educai.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.LoginRequest
import com.example.educai.data.model.LoginResponse
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    val loginResponse = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<ErrorResponse>()

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
}