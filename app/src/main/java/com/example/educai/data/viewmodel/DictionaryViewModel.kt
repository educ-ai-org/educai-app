package com.example.educai.data.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.WordDefinition
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DictionaryViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    var wordDefinition = MutableLiveData<WordDefinition?>()
        private set
    val errorMessage = MutableLiveData<ErrorResponse>()
    val isLoading = MutableLiveData<Boolean>()

    fun getWordDefinition(word : String) {
        isLoading.value = true

        val call = RetrofitInstance.dictionaryService.getWordDefinition(word)

        call.enqueue(object : Callback<WordDefinition> {
            override fun onResponse(call: Call<WordDefinition>, response: Response<WordDefinition>) {
                if (response.isSuccessful) {
                    wordDefinition.postValue(response.body())
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<WordDefinition>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
                isLoading.value = false
            }
        })
    }

    fun playAudio(audioUrl: String) {
        stopAudio()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
            setOnPreparedListener { start() }
            setOnCompletionListener { stopAudio() }
            prepareAsync()
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}