package com.example.educai.data.viewmodel

import android.content.Context
import android.os.Environment
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MaterialViewModel : ViewModel() {
    var material = MutableLiveData<File?>()
        private set
    var isLoading = MutableLiveData(false)
        private set
    var errorMessage = MutableLiveData<ErrorResponse>()

    fun postMaterial(
        context: Context,
        instructions: String?,
        youtubeLink: String?,
        audio: MultipartBody.Part?,
        document: MultipartBody.Part?
    ) {
        isLoading.postValue(true)

        val call = RetrofitInstance.materialService
            .generateEducationalMaterial(instructions, youtubeLink, audio, document)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        val file = savePdfToFile(context, responseBody)
                        material.postValue(file)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage.postValue(
                        errorBody?.getErrorMessageFromJson()
                    )
                }
                isLoading.postValue(false)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                errorMessage.postValue(t.message.toString().getErrorMessageFromJson())
                isLoading.postValue(false)
            }
        })
    }

    private fun savePdfToFile(context: Context, body: ResponseBody): File? {
        return try {
            val file = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                "educational_material.pdf"
            )
            body.byteStream().use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
