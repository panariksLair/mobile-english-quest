package com.github.panarik.learningenglishquiz.ui.downloading

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.panarik.learningenglishquiz.ui.home.model.Quiz
import com.github.panarik.learningenglishquiz.ui.home.model.QuizSession
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val TAG = "DownloadingViewModel"

class DownloadingViewModel : ViewModel() {

    private lateinit var fragment: DownloadingFragment
    private val quiz = MutableLiveData<Quiz?>()

    fun init(fragment: DownloadingFragment): DownloadingViewModel {
        this.fragment = fragment
        quiz.observe(fragment.viewLifecycleOwner) {
            if (it != null) {
                fragment.startQuizFragment()
            } else {
                Toast.makeText(fragment.context, "Received empty Quiz", Toast.LENGTH_SHORT).show()
                downloadQuiz()
            }
        }
        return this
    }

    fun downloadQuiz() {
        Log.d(TAG, "Downloading Quiz...")
        val request = Request.Builder()
            .url("https://mxkrc6qenp.eu-central-1.awsapprunner.com/quiz")
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to request quiz. Original exception: ${e.message}")
                fragment.activity?.runOnUiThread {
                    quiz.value = Quiz("", "", emptyList(), "")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "Response from Quiz server received.")
                val code = response.code
                val body = response.body?.string() ?: ""
                if (code == 200) {
                    Log.d(TAG, "Quiz downloaded successfully. Quiz body: $body")
                    buildQuiz(body)
                } else {
                    Log.e(
                        TAG,
                        "Failed to request. Response: code=$code body=${body}"
                    )
                    fragment.activity?.runOnUiThread {
                        quiz.value = null
                    }
                }

            }

        })
    }

    private fun buildQuiz(body: String) {
        Log.d(TAG, "Parsing Quiz body...")
        val quiz: Quiz? = try {
            val quizSession =
                jacksonObjectMapper().readValue(body, QuizSession::class.java)
            Log.d(TAG, "Quiz parsed successfully. Quiz: ${quizSession.quiz}")
            quizSession.quiz
        } catch (e: Exception) {
            Log.e(TAG, "Error caught during Quiz parsing. Original exception: ${e.message}")
            null
        }
        fragment.activity?.runOnUiThread {
            val isValid = quiz?.isValid() == true
            if (isValid) {
                Log.d(TAG, "Quiz is valid.")
                Toast.makeText(fragment.context, "Quiz is valid", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(fragment.context, "Quiz is invalid", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Quiz is invalid.")
            }
            this.quiz.value = if (isValid) quiz else null
        }
    }

}