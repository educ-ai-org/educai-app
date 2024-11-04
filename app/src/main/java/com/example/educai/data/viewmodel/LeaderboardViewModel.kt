package com.example.educai.data.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.Leaderboard
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import kotlinx.coroutines.launch

class LeaderboardViewModel : ViewModel() {
    var leaderboard = mutableStateOf<List<Leaderboard>>(emptyList())
    val errorMessage = MutableLiveData<ErrorResponse>()
    var isLoading = mutableStateOf(false)

    suspend fun getLeaderboard(classroomId: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = RetrofitInstance.leaderboardService.getLeaderboard(classroomId)
                if (response.isSuccessful) {
                    leaderboard.value = response.body() ?: emptyList()
                    fetchStudentPictures(classroomId)
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message?.getErrorMessageFromJson())
            } finally {
                isLoading.value = false
            }
        }
    }

    private suspend fun fetchStudentPictures(classroomId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.userService.getProfilePictures(classroomId)
                if (response.isSuccessful) {

                    val studentPictures = response.body() ?: emptyList()
                    leaderboard.value = leaderboard.value.map { student ->
                        val studentPicture = studentPictures.find { it.id == student.id }
                        student.copy(profilePicture = studentPicture?.profilePicture)
                    }

                } else {
                    Log.e("LeaderboardViewModel", "Error fetching student pictures: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("LeaderboardViewModel", "Error fetching student pictures", e)
            }
        }
    }

}
