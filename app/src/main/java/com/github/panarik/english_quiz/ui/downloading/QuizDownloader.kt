package com.github.panarik.english_quiz.ui.downloading

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.panarik.english_quiz.ui.home.model.QuizSession
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val TAG = "QuizDownloader"

class QuizDownloader(val fragment: FragmentActivity?, val liveData: MutableLiveData<QuizSession?>) {

    fun downloadQuiz() {
        Log.d(TAG, "Downloading new Quiz...")
        val request = Request.Builder()
            .url("https://mxkrc6qenp.eu-central-1.awsapprunner.com/quiz")
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to request quiz. Original exception: ${e.message}")
                fragment?.runOnUiThread { liveData.value = null }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "Response from Quiz server received.")
                val code = response.code
                val body = response.body?.string() ?: ""
                if (code == 200) {
                    Log.d(TAG, "Quiz downloaded successfully. Quiz body: $body")
                    val quiz = buildQuiz(body)
                    fragment?.runOnUiThread {
                        if (quiz != null) {
                            Log.d(TAG, "Quiz is valid.")
                            liveData.value = quiz
                        } else {
                            Log.e(TAG, "Quiz is invalid.")
                            liveData.value = null
                        }
                    }
                } else {
                    Log.e(TAG, "Failed to request. Response: code=$code body=${body}")
                    fragment?.runOnUiThread { liveData.value = null }
                }
            }
        })
    }

    private fun buildQuiz(body: String): QuizSession? {
        Log.d(TAG, "Parsing Quiz body...")
        return try {
            val quizSession = jacksonObjectMapper().readValue(body, QuizSession::class.java)
            Log.d(
                TAG,
                "Quiz session is parsed successfully. Session id=${quizSession.sessionId} quiz=${quizSession.quiz}"
            )
            if (quizSession.isValid()) quizSession else null
        } catch (e: Exception) {
            Log.e(TAG, "Error caught during Quiz parsing. Original exception: ${e.message}")
            null
        }
    }

}