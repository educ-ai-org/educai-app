package com.example.educai.data.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.MainActivity
import com.example.educai.data.model.AnsweredClasswork
import com.example.educai.data.model.AnsweredQuestion
import com.example.educai.data.model.Classwork
import com.example.educai.data.model.ClassworkReview
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.toDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ClassworkViewModel: ViewModel() {
    val classwork = MutableLiveData<Classwork>()
    var questionsAnswers: MutableList<AnsweredQuestion> = mutableListOf()

    val classworkReview = MutableLiveData<ClassworkReview>()

    fun getClasswork(classworkId: String) {
        val call = RetrofitInstance.classworkService.getClassworkById(classworkId)

        call.enqueue(object : Callback<Classwork> {
            override fun onResponse(
                call: Call<Classwork>,
                response: Response<Classwork>
            ) {
                if(response.isSuccessful) {
                    val classworkItem = response.body()

                    classworkItem?.let {
                        it.endDate = it.endDate.toDate()
                        classwork.postValue(response.body())
                    }

                    questionsAnswers.clear()
                }
            }

            override fun onFailure(call: Call<Classwork>, t: Throwable) {
                Log.d("Deu erro", "ERROR")
            }
        })
    }

    fun changeQuestionAnswer(answeredQuestion: AnsweredQuestion) {
        val answeredExists = questionsAnswers.any { question -> question.questionId == answeredQuestion.questionId }

        if(answeredExists) {
            val index = questionsAnswers.indexOfFirst { it.questionId == answeredQuestion.questionId }

            if (index != -1) {
                questionsAnswers[index].optionKey = answeredQuestion.optionKey
            }
        } else {
            questionsAnswers.add(answeredQuestion)
        }
    }

    fun sendClasswork(classworkId: String) {
        if(questionsAnswers.size != classwork.value?.questions?.size) {
            return Toast.makeText(MainActivity.context, "É necessário responder todas as questões", Toast.LENGTH_SHORT).show()
        }

        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val payload = AnsweredClasswork(
            datePosting = currentDate.format(formatter),
            questionAnswers = questionsAnswers
        )

        val call = RetrofitInstance.classworkService.sendClasswork(
            classworkId = classworkId,
            classwork = payload
        )

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful) {
                    return Toast.makeText(MainActivity.context, "Atividade enviada!", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(MainActivity.context, "Não foi possível enviar a atividade, tente novamente mais tarde", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MainActivity.context, "Não foi possível enviar a atividade, tente novamente mais tarde", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getClassworkReview(classworkId: String) {
        val call = RetrofitInstance.classworkService.getClassworkReview(classworkId)

        call.enqueue(object : Callback<ClassworkReview> {
            override fun onResponse(
                call: Call<ClassworkReview>,
                response: Response<ClassworkReview>
            ) {
                if(response.isSuccessful) {
                    val classworkItem = response.body()

                    classworkItem?.let {
                        it.classwork.endDate = it.classwork.endDate.toDate()
                        classworkReview.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<ClassworkReview>, t: Throwable) {
                Toast.makeText(MainActivity.context, "Não foi possível consultar sua revisão, tente novamente mais tarde", Toast.LENGTH_SHORT).show()
            }
        })
    }
}