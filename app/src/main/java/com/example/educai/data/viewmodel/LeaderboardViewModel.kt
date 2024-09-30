package com.example.educai.data.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.Leaderboard
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderboardViewModel : ViewModel() {
    var leaderboard = mutableStateOf<List<Leaderboard>>(emptyList())
        private set
    val errorMessage = MutableLiveData<ErrorResponse>()
    var isLoading = mutableStateOf(false)

    fun getLeaderboard(classroomId: String) {
        isLoading.value = true

        val call = RetrofitInstance.leaderboardService.getLeaderboard(classroomId)

        call.enqueue(object : Callback<List<Leaderboard>> {
            override fun onResponse(call: Call<List<Leaderboard>>, response: Response<List<Leaderboard>>) {
                if (response.isSuccessful) {
                    leaderboard.value = response.body() ?: emptyList()
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<List<Leaderboard>>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
                isLoading.value = false
            }
        })
    }
}
