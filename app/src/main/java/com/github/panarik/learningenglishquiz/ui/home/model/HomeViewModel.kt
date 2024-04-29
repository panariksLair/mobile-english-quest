package com.github.panarik.learningenglishquiz.ui.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.learningenglishquiz.ui.home.HomeFragment

class HomeViewModel : ViewModel() {

    private lateinit var fragment: HomeFragment
    private val currentQuiz = MutableLiveData<Quiz>()
    private val newQuiz = MutableLiveData<Quiz>()


    fun init(fragment: HomeFragment):HomeViewModel {
        this.fragment = fragment
        return this
    }

    /**
     * Check current Quiz. If it null. run loading screen and then create new fragment with already downloaded Quiz.
     *
     */
    fun createQuiz() {
        if (currentQuiz.value == null) {
            fragment.startLoadingFragment()
        } else {
            // 2. Update fragment with Quiz data.
        }
    }

    fun startQuiz() {
        // 1. Download new Quiz in separated thread.
        // 2. Run timer
        // 3. Wait users action and create new fragment
    }

}