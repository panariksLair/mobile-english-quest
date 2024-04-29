package com.github.panarik.learningenglishquiz.ui.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.learningenglishquiz.ui.home.HomeFragment

class HomeViewModel : ViewModel() {

    private lateinit var fragment: HomeFragment
    private val currentQuiz = MutableLiveData<Quiz>()
    private val newQuiz = MutableLiveData<Quiz>()


    fun init(fragment: HomeFragment) {
        this.fragment = fragment
        createQuiz()
        startQuiz()
    }

    private fun startQuiz() {
        // 1. Download new Quiz in separated thread.
        // 2. Run timer
        // 3. Wait users action and create new fragment
    }

    /**
     * Check current Quiz. If it null. run loading screen and then create new fragment with already downloaded Quiz.
     *
     */
    private fun createQuiz() {
        if (currentQuiz.value == null) {
//            fragment.activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.home_layout, DownloadingFragment(), "nextFlag")
//                ?.addToBackStack(null)
//                ?.commit()
        } else {
            // 2. Update fragment with Quiz data.
        }
    }


}