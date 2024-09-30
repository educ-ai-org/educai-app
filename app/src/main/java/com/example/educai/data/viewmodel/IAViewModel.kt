package com.example.educai.data.viewmodel

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.MainActivity
import com.example.educai.data.model.EduRequest
import com.example.educai.data.model.EduResponse
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.model.Message
import com.example.educai.data.model.MessageType
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.Locale

class IAViewModel: ViewModel() {
    private val _messages = MutableLiveData<MutableList<Message>>(mutableListOf())

    var eduHasSpeaking by mutableStateOf<Boolean>(false)
        private set

    val messages: LiveData<MutableList<Message>> get() = _messages

    val eduResponse = MutableLiveData<EduResponse>()
    val errorMessage = MutableLiveData<ErrorResponse>()

    private val tts = TextToSpeech(MainActivity.context) { status ->
        if (status == TextToSpeech.SUCCESS){
            Log.d("TextToSpeech", "Initialization Success")
            configureTTSVoice()
        } else{
            Log.d("TextToSpeech", "Initialization Failed")
        }
    }

    private fun configureTTSVoice() {
        val voices = tts.voices
        val maleVoice = voices.firstOrNull { voice ->
            voice.name.contains("male", ignoreCase = true) && voice.locale == Locale.US
        }

        tts.language = Locale.US
        if (maleVoice != null) {
            tts.voice = maleVoice
            Log.d("TTS", "Voz masculina selecionada: ${maleVoice.name}")
        } else {
            Log.e("TTS", "Nenhuma voz masculina encontrada. Usando a voz padrão.")
        }
    }

    fun speakMessage(text: String) {
        eduHasSpeaking = false
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
    }

    fun addMessage(message: Message) {
        _messages.value?.add(message)
        _messages.value = _messages.value
    }

    fun removeLastMessage() {
        _messages.value?.removeLastOrNull()
        _messages.value = _messages.value
    }

    fun stopSpeak() {
        tts.stop()
    }

    fun getEduResponse(eduRequest: EduRequest) {
        eduHasSpeaking = true

        val call = RetrofitInstance.iaService.getEduResponse(eduRequest)

        call.enqueue(object : Callback<EduResponse> {
            override fun onResponse(call: Call<EduResponse>, response: Response<EduResponse>) {
                if (response.isSuccessful) {
                    removeLastMessage()

                    val message = response.body()

                    if (message != null) {
                        addMessage(
                            Message(
                                text = message.response,
                                date = LocalDateTime.now(),
                                type = MessageType.RECEIVE
                            )
                        )

                        speakMessage(message.response)
                    }

                    eduResponse.postValue(response.body())
                } else {
                    errorMessage.postValue(response.errorBody()?.string()?.getErrorMessageFromJson())
                }
            }

            override fun onFailure(call: Call<EduResponse>, t: Throwable) {
                removeLastMessage()

                addMessage(
                    Message(
                        text = "Desculpe, não consegui me conectar com minha base de dados, poderia repetir?",
                        date = LocalDateTime.now(),
                        type = MessageType.RECEIVE
                    )
                )

                speakMessage("Desculpe, não consegui me conectar com minha base de dados, poderia repetir?")
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
            }
        })
    }
}
