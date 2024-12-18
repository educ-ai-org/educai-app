package com.example.educai.data.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educai.data.model.Classroom
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.Participant
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    var classrooms = mutableStateOf<List<Classroom>>(emptyList())
        private set
    var participants = mutableStateOf<List<Participant>>(emptyList())
        private set
    val userPictureUrl: MutableLiveData<String?> = MutableLiveData()
    val errorMessage = MutableLiveData<ErrorResponse>()
    val isLoading = MutableLiveData<Boolean>()

    fun getUserClassrooms() {
        isLoading.value = true

        val call = RetrofitInstance.userService.getUserClassrooms()

        call.enqueue(object : Callback<List<Classroom>> {
            override fun onResponse(call: Call<List<Classroom>>, response: Response<List<Classroom>>) {
                if (response.isSuccessful) {
                    classrooms.value = response.body() ?: emptyList()
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }

                isLoading.value = false
            }

            override fun onFailure(call: Call<List<Classroom>>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
                isLoading.value = false
            }
        })
    }

    fun getParticipantsByClassId(classroomId: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = RetrofitInstance.userService.getParticipantsByClassId(classroomId)
                if (response.isSuccessful) {
                    participants.value = response.body() ?: emptyList()
                    fetchProfilePictures(classroomId)
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

    fun getUserPictureUrl() {

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.userServiceScalars.getUserPictureUrl()

                if (response.isSuccessful) {
                  userPictureUrl.value = response.body()

                } else {
                    userPictureUrl.value = null
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", e.toString())
                userPictureUrl.value = null
            }
        }
    }

    private suspend fun fetchProfilePictures(classroomId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.userService.getProfilePictures(classroomId)
                if (response.isSuccessful) {

                    val pictures = response.body() ?: emptyList()
                    participants.value = participants.value.map { participant ->
                        val userPicture = pictures.find { it.id == participant.id }
                        participant.copy(profilePicture = userPicture?.profilePicture)
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