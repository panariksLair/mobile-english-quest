package com.github.panarik.learningenglishquiz.ui.home.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.learningenglishquiz.ui.home.HomeFragment

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private lateinit var fragment: HomeFragment
    private val currentQuiz = MutableLiveData<Quiz>()
    private val newQuiz = MutableLiveData<Quiz>()

    fun init(fragment: HomeFragment): HomeViewModel {
        this.fragment = fragment
        return this
    }

    /**
     * Check current Quiz. If it null. run loading screen and then create new fragment with already downloaded Quiz.
     *
     */
    fun createQuiz() {
        val session: QuizSession? =
            fragment.arguments?.getSerializable("QuizSession") as QuizSession?
        if (session != null) {
            Log.d(TAG, "Quiz is ready. Starting Quiz...")
            startQuiz(session)
        } else {
            Log.d(TAG, "Quiz is not ready. Downloading new Quiz...")
            fragment.startLoadingFragment()
        }


    }

    private fun startQuiz(session: QuizSession) {
        fragment.createScreen(session)
        // 1. Set views.
        // 2. Run timer
        // 3. Download new Quiz.
        // 3. Wait users action and create new fragment with downloaded quiz.
    }

}