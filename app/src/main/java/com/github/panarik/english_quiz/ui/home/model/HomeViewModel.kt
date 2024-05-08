package com.github.panarik.english_quiz.ui.home.model

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.english_quiz.ui.downloading.QuizDownloader
import com.github.panarik.english_quiz.ui.home.HomeFragment

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private lateinit var fragment: HomeFragment

    var gameState: GameStates = GameStates.WAITING_USER_ACTION
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
            gameState = GameStates.QUIZ_FINISHED_SUCCESS
            Toast.makeText(fragment.context, "You Won!", LENGTH_SHORT).show()
            fragment.startNextQuiz()
        } else {
            gameState = GameStates.QUIZ_FINISHED_FAILED
            Toast.makeText(fragment.context, "You Lose!", LENGTH_SHORT).show()
            fragment.startNextQuiz()
        }
    }

}