package com.github.panarik.learningenglishquiz.ui.home.model

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.learningenglishquiz.ui.home.HomeFragment

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private lateinit var fragment: HomeFragment
    private val currentQuiz = MutableLiveData<QuizSession>()
    private val newQuiz = MutableLiveData<QuizSession>()

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
        } else {
            Log.d(TAG, "Quiz is not ready. Downloading new Quiz...")
            fragment.startLoadingFragment()
        }
    }

    fun checkQuiz(buttonNumber: Int) {
        if (currentQuiz.value?.answers?.get(buttonNumber)?.isRight == true) {
            fragment.finishQuiz(buttonNumber)
            Toast.makeText(fragment.context, "You Won!", LENGTH_SHORT).show()
        } else {
            fragment.finishQuiz(buttonNumber)
            Toast.makeText(fragment.context, "You Lose!", LENGTH_SHORT).show()
        }
    }

}