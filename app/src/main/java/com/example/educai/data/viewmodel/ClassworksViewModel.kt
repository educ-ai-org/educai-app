package com.example.educai.data.viewmodel

import retrofit2.Call
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.data.model.Classwork
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import retrofit2.Response
import retrofit2.Callback

class ClassworksViewModel : ViewModel(){

    var classworks = MutableLiveData<List<Classwork>>(emptyList())
        private set
    var isLoading = mutableStateOf(false)
    var errorMessage = MutableLiveData<ErrorResponse>()

     fun getClassworks(classroomId: String){
        isLoading.value = true

         val call = RetrofitInstance.classworkService.getClassworks(classroomId)
         object : Callback<List<Classwork>> {
            override fun onResponse(call: Call<List<Classwork>>, response: Response<List<Classwork>>) {
                if (response.isSuccessful) {
                    classworks.postValue(response.body() ?: emptyList())
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
                    isLoading.value = false
                }
            override fun onFailure(call: Call<List<Classwork>>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
                isLoading.value = false
            }
         }
     }

}