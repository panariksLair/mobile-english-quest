package com.github.panarik.learningenglishquiz.ui.home.model

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.learningenglishquiz.ui.home.HomeFragment

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
        Toast.makeText(fragment.context, "Args: ${fragment.args.testArgument}", LENGTH_SHORT)
            .show()
        if (fragment.args.testArgument != "test argument") {
            fragment.startLoadingFragment()
        }
    }

    fun startQuiz() {
        // 1. Download new Quiz in separated thread.
        // 2. Run timer
        // 3. Wait users action and create new fragment
    }

}