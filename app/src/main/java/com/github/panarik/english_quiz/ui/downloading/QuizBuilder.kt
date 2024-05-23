package com.github.panarik.english_quiz.ui.downloading

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.panarik.english_quiz.MainActivity
import com.github.panarik.english_quiz.services.AppFlags
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

class QuizBuilder(
    val fragment: FragmentActivity?, private val liveData: MutableLiveData<QuizSession?>
) {

    private var quizesFromDatabase: List<QuizSession> = emptyList()


    fun buildQuiz() {
        val activity = (fragment as MainActivity)
        fragment.lifecycleScope.launch {
            val quiz = buildFromDatabase(activity)
            if (quiz == null) downloadQuiz()
            else liveData.value = quiz
        }
    }

    private suspend fun buildFromDatabase(activity: MainActivity): QuizSession? {
        Log.d(TAG, "Getting Quiz from database...")
        waitDatabaseSetup()
        if (!AppFlags.isDatabaseReady()) {
            Log.e(TAG, "Failed to wait database setup. Something went wrong")
            return null
        }
        return attemptToGetFromDatabase(activity)
    }

    /**
     * Database setup process in Main activity.
     */
    private suspend fun waitDatabaseSetup() {
        var attempt = 1
        while (attempt < 5 && !AppFlags.isDatabaseReady()) {
            Log.d(TAG, "Wait database to setup... Attempt=$attempt")
            delay(1000)
            if (AppFlags.isDatabaseReady()) {
                Log.d(TAG, "Database setup complete.")
                return
            } else {
                attempt++
            }
        }
    }

    private suspend fun attemptToGetFromDatabase(activity: MainActivity): QuizSession? {
        Log.d(TAG, "Trying to get quiz list from database.")
        quizesFromDatabase = activity.db.dao.getQuizes().map { it.toQuizSession() }
        return if (quizesFromDatabase.isNotEmpty()) {
            Log.d(TAG, "Quiz received successfully.")
            val quiz = if (quizesFromDatabase.size > 1) {
                Log.d(TAG, "Choose random Quiz from database.")
                quizesFromDatabase[Random.nextInt(quizesFromDatabase.lastIndex)]
            } else {
                Log.d(TAG, "Only one Quiz remaining in database. Choose last one.")
                quizesFromDatabase[0]
            }
            Log.d(TAG, "Created Quiz: ${quiz.quiz}")
            quiz
        } else {
            Log.d(TAG, "Database is empty.")
            null
        }
    }

    private fun downloadQuiz() {
        Log.d(TAG, "Downloading new Quiz...")
        val client = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build()
        val request =
            Request.Builder().url("https://mxkrc6qenp.eu-central-1.awsapprunner.com/quiz").build()
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
                            Thread.sleep(1000)
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