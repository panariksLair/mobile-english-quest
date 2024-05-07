package com.github.panarik.learningenglishquiz.ui.downloading

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.panarik.learningenglishquiz.ui.home.model.QuizSession

private const val TAG = "DownloadingViewModel"

class DownloadingViewModel : ViewModel() {

    private lateinit var fragment: DownloadingFragment
    private val quiz = MutableLiveData<QuizSession?>()
    private val quizBody = MutableLiveData<String?>()

    fun init(fragment: DownloadingFragment): DownloadingViewModel {
        this.fragment = fragment
        quizBody.observe(fragment.viewLifecycleOwner) {
            if (it != null) buildQuiz(it)
            else {
                Toast.makeText(fragment.context, "Received empty Quiz", LENGTH_SHORT).show()
                downloadQuiz()
            }
        }
        quiz.observe(fragment.viewLifecycleOwner) {
            if (it != null) fragment.startQuizFragment(it)
            else {
                Toast.makeText(fragment.context, "Received empty Quiz", LENGTH_SHORT).show()
                downloadQuiz()
            }
        }
        return this
    }

    private fun buildQuiz(body: String) {
        Log.d(TAG, "Parsing Quiz body...")
        val quiz: QuizSession? = try {
            val quizSession =
                jacksonObjectMapper().readValue(body, QuizSession::class.java)
            Log.d(
                TAG,
                "Quiz session is parsed successfully. Session id=${quizSession.sessionId} quiz=${quizSession.quiz}"
            )
            quizSession
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

    fun downloadQuiz() {
        QuizDownloader(fragment.activity, quizBody).downloadQuiz()
    }

}