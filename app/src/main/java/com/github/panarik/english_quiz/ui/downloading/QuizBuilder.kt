package com.github.panarik.english_quiz.ui.downloading

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.panarik.english_quiz.MainActivity
import com.github.panarik.english_quiz.ui.home.model.QuizSession
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private const val TAG = "[QuizBuilder]"

class QuizBuilder(val fragment: FragmentActivity?, val liveData: MutableLiveData<QuizSession?>) {

    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    fun buildQuiz() {
        buildFromDatabase()
    }

    private fun buildFromDatabase() {
        val activity = fragment as MainActivity
        activity.lifecycleScope.launch {
            Log.d(TAG, "Getting quiz from database.")
            var quizes: List<QuizSession> = emptyList()
            while (quizes.isEmpty()) {
                Log.d(TAG, "Trying to get quiz from database...")
                quizes = activity.db.dao.getAllQuizes().map { it.toQuizSession() }
                if (quizes.isNotEmpty()) {
                    Log.d(TAG, "Quiz received successfully.")
                    val quiz = quizes[Random.nextInt(quizes.lastIndex)]
                    Log.d(TAG, "Created Quiz: ${quiz.quiz}")
                    liveData.value = quiz
                } else {
                    Log.d(TAG, "Can't receive Quiz. Waiting one second to database update.")
                    delay(1000)
                    liveData.value = null
                }
            }

        }
    }

    private fun downloadQuiz() {
        Log.d(TAG, "Downloading new Quiz...")
        val request = Request.Builder()
            .url("https://mxkrc6qenp.eu-central-1.awsapprunner.com/quiz")
            .build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to request quiz. Original exception: ${e.message}")
                Thread.sleep(1000)
                fragment?.runOnUiThread { liveData.value = null }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "Response from Quiz server received.")
                val code = response.code
                val body = response.body?.string() ?: ""
                if (code == 200) {
                    Log.d(TAG, "Quiz downloaded successfully. Quiz body: $body")
                    val quiz = parseQuiz(body)
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
                    Thread.sleep(1000)
                    fragment?.runOnUiThread { liveData.value = null }
                }
            }
        })
    }

    private fun parseQuiz(body: String): QuizSession? {
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