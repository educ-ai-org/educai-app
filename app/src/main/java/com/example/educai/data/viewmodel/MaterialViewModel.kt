package com.example.educai.data.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.FileProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educai.data.model.ErrorResponse
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.getErrorMessageFromJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import okio.BufferedSink
import okio.source
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MaterialViewModel : ViewModel() {
    var material = MutableLiveData<File?>()
        private set
    var isLoading = MutableLiveData(false)
        private set
    var errorMessage = MutableLiveData<ErrorResponse?>()

    fun postMaterial(
        context: Context,
        instructions: String?,
        youtubeLink: String?,
        audioUri: Uri?,
        documentUri: Uri?
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val documentPart = documentUri?.let { uri ->
                    createPartFromUri(context, uri, "application/pdf", "document.pdf", "document")
                }

                val audioPart = audioUri?.let { uri ->
                    createPartFromUri(context, uri, "audio/mpeg", "audio.mp3", "audio")
                }

                if (instructions == null && youtubeLink == null && audioPart == null && documentPart == null) {
                    errorMessage.value = ErrorResponse(
                        error = "ValidationError",
                        message = "Multipart body must have at least one part.",
                        path = "postMaterial",
                        status = 400,
                        timestamp = System.currentTimeMillis().toString()
                    )
                    isLoading.value = false
                    return@launch
                }

                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.materialService.generateEducationalMaterial(
                        instructions.takeIf { !it.isNullOrEmpty() },
                        youtubeLink.takeIf { !it.isNullOrEmpty() },
                        audioPart,
                        documentPart
                    ).execute()
                }
                handleResponse(context, response)
            } catch (e: Exception) {
                errorMessage.value = ErrorResponse(
                    error = "ValidationError",
                    message = "Multipart body must have at least one part.",
                    path = "postMaterial",
                    status = 400,
                    timestamp = System.currentTimeMillis().toString()
                )
                Log.e("MaterialViewModel", "Erro ao enviar material: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }

    private fun createPartFromUri(
        context: Context, uri: Uri, mimeType: String, fileName: String, partName: String
    ): MultipartBody.Part? {
        return if (uri.scheme == "content") {
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.let { stream ->
                val requestBody = object : RequestBody() {
                    override fun contentType() = mimeType.toMediaTypeOrNull()
                    override fun writeTo(sink: BufferedSink) {
                        sink.writeAll(stream.source())
                    }
                }
                MultipartBody.Part.createFormData(partName, fileName, requestBody)
            }
        } else {
            Log.e("MaterialViewModel", "O URI não é do tipo 'content': $uri")
            null
        }
    }

    private fun handleResponse(context: Context, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                val file = savePdfToFile(context, responseBody)
                material.value = file
                file?.let { openPdfFile(context, it) }
            }
        } else {
            val errorBody = response.errorBody()?.string()
            errorMessage.value = errorBody?.getErrorMessageFromJson()
        }
    }

    private fun savePdfToFile(context: Context, body: ResponseBody): File? {
        return try {
            val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (downloadsDir != null && !downloadsDir.exists()) {
                downloadsDir.mkdirs()
            }
            val file = File(downloadsDir, "educational_material.pdf")
            body.byteStream().use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            Log.d("MaterialViewModel", "PDF salvo em: ${file.absolutePath}")
            file
        } catch (e: Exception) {
            Log.e("MaterialViewModel", "Erro ao salvar PDF: ${e.message}")
            null
        }
    }

    private fun openPdfFile(context: Context, file: File) {
        if (file.exists()) {
            val uri: Uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
            Log.d("MaterialViewModel", "Abrindo PDF: ${file.absolutePath}")
        } else {
            Log.e("MaterialViewModel", "Arquivo PDF não encontrado: ${file.absolutePath}")
        }
    }
}
