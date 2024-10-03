package com.example.educai.data.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educai.MainActivity
import com.example.educai.data.model.AnsweredQuestion
import com.example.educai.data.model.Classwork
import com.example.educai.data.network.RetrofitInstance
import com.example.educai.utils.toDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassworkViewModel: ViewModel() {
    val classwork = MutableLiveData<Classwork>()
    var questionsAnswers: List<AnsweredQuestion> = emptyList()

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

                    questionsAnswers = emptyList()
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
            questionsAnswers.plus(answeredQuestion)
        }
    }

    fun sendClasswork() {
        if(questionsAnswers.size != classwork.value?.questions?.size) {
            return Toast.makeText(MainActivity.context, "É necessário responder todas as questões", Toast.LENGTH_SHORT).show()
        }


    }
}