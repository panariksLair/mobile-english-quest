package com.github.panarik.english_quiz.ui.home.model

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.panarik.english_quiz.services.model.QuizRate
import com.github.panarik.english_quiz.ui.downloading.QuizDownloader
import com.github.panarik.english_quiz.ui.home.HomeFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

private const val TAG = "[HomeViewModel]"

class HomeViewModel : ViewModel() {

    private lateinit var fragment: HomeFragment

    var gameState = MutableLiveData<GameStates>()
    private val currentQuiz = MutableLiveData<QuizSession>()
    val newQuiz = MutableLiveData<QuizSession?>()

    fun init(fragment: HomeFragment): HomeViewModel {
        this.fragment = fragment
        return this
    }

    /**
     * Check current Quiz. If it null. run loading screen and then create new fragment with already downloaded Quiz.
     *
     */
    fun startQuiz() {
        currentQuiz.value = fragment.arguments?.getSerializable("QuizSession") as QuizSession?
        if (currentQuiz.value != null) {
            Log.d(TAG, "Quiz is ready. Starting Quiz...")
            val answers = mutableListOf<QuizAnswer>()
            answers.add(QuizAnswer(currentQuiz.value!!.quiz.right_answer, true))
            answers.add(QuizAnswer(currentQuiz.value!!.quiz.wrong_answers[0], false))
            answers.add(QuizAnswer(currentQuiz.value!!.quiz.wrong_answers[1], false))
            answers.add(QuizAnswer(currentQuiz.value!!.quiz.wrong_answers[2], false))
            answers.shuffle()
            answers.toList()
            currentQuiz.value!!.answers = answers
            fragment.createScreen(currentQuiz.value!!)
            gameState.value = GameStates.WAITING_USER_ACTION

            // Download next quiz also.
            QuizDownloader(fragment.activity, newQuiz).downloadQuiz()
        } else {
            Log.d(TAG, "Quiz is not ready. Downloading new Quiz...")
            fragment.startLoadingFragment()
        }
    }

    fun checkQuiz(buttonNumber: Int) {
        currentQuiz.value?.let { fragment.finishQuiz(it) }
        if (currentQuiz.value?.answers?.get(buttonNumber)?.isRight == true) {
            gameState.value = GameStates.QUIZ_FINISHED_SUCCESS
            fragment.showWinIcon()
        } else {
            gameState.value = GameStates.QUIZ_FINISHED_FAILURE
        }
    }

    fun rateQuiz(rate: Int) {
        val quizRate = QuizRate(currentQuiz.value?.sessionId ?: "", rate)
        try {
            val body = jacksonObjectMapper().writeValueAsBytes(quizRate).toRequestBody()
            val request =
                Request.Builder().url("https://mxkrc6qenp.eu-central-1.awsapprunner.com/rate")
                    .post(body).build()
            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "Can't send QuizRate. Message=${e.message} QuizRate=${quizRate}")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(
                        TAG,
                        "QuizRate is sent successfully. Response code=${response.code} body=${response.body?.string()}"
                    )
                    fragment.activity?.runOnUiThread {
                        Toast.makeText(fragment.context, "Thank You!", LENGTH_SHORT).show()
                    }
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "Can't QuizRate to server. Message=${e.message} QuizRate=${quizRate}")
        }
    }

}